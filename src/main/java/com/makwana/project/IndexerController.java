package com.makwana.project;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.makwana.project.dao.AdminDao;
import com.makwana.project.dao.AppointmentBookDao;
import com.makwana.project.dao.DoctorDao;
import com.makwana.project.dao.FamailyMemberDao;
import com.makwana.project.dao.LeaveDao;
import com.makwana.project.dao.LoginDao;
import com.makwana.project.dao.PatientDao;
import com.makwana.project.dao.ReceptionistDao;
import com.makwana.project.dao.UrgantAppointmentBookDao;
import com.makwana.project.entity.Admin;
import com.makwana.project.entity.Appointment;
import com.makwana.project.entity.Doctor;
import com.makwana.project.entity.DoctorLeave;
import com.makwana.project.entity.FamailyMember;
import com.makwana.project.entity.Login;
import com.makwana.project.entity.Patient;
import com.makwana.project.entity.Receptionist;
import com.makwana.project.entity.UrgantAppointmentBook;
import com.mysql.fabric.xmlrpc.base.Member;

@Controller
public class IndexerController {

	@Autowired
	ReceptionistDao rdao;
	
	@Autowired
	PatientDao pdao;

	@Autowired
	DoctorDao ddao;

	@Autowired
	LoginDao ldao;

	@Autowired
	AppointmentBookDao abdao;
	
	@Autowired
	LeaveDao leaveDao;
	
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	FamailyMemberDao famailyMemberDao;
	
	@Autowired
	UrgantAppointmentBookDao urgantAppointmentBookDao;

	@RequestMapping("/addPatient")
	public ModelAndView addPatientDetails(HttpServletRequest request, HttpServletResponse response) {
		String name= request.getParameter("name");
		String mobileNumber=request.getParameter("mobileNumber");
		String gender=request.getParameter("gender");
		int age=Integer.parseInt(request.getParameter("age"));
		String address=request.getParameter("address");
		String username=request.getParameter("user_name");
		String password=request.getParameter("password");
		Patient patient=new Patient();
		patient.setAddress(address);
		patient.setAge(age);
		patient.setGender(gender);
		patient.setMobileNumber(mobileNumber);
		patient.setName(name);
		patient.setUser_name(username);
		patient.setPassword(password);
		
		Login login=new Login();
		login.setUser_name(username);
		login.setPassword(password);
		login.setType("Patient");
		
		pdao.addParentPatient(patient, login);
		FamailyMember famailyMember=new FamailyMember();
		famailyMember.setGender(gender);
		famailyMember.setAge(age);
		famailyMember.setName(name);
		
		famailyMemberDao.addParentPatient(famailyMember, login);
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("DisplayPatient.jsp");
		mv.addObject("PatientDetails", patient);
		
		return mv;
	}

	@RequestMapping("/ShowAll")
	public ModelAndView show() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("DisplayAllDetails.jsp");
		List<FamailyMember> pt = famailyMemberDao.getAllFamilyMember();
		mv.addObject("AllPatient", pt);
		mv.addObject("AppointmentBookDao",abdao);
		
		
		return mv;
	}

	@RequestMapping("/addDoctor")
	public ModelAndView addDoctor(@ModelAttribute Doctor doctor, @ModelAttribute Login login) {
		ddao.addDoctor(doctor, login);
		ModelAndView mv = new ModelAndView();
		mv.addObject("DoctorDetails", doctor);
		mv.setViewName("DisplayDoctor.jsp");
		
		return mv;
	}

	@RequestMapping("/ShowAllDoctor")
	public ModelAndView show1() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("DisplayAllDoctor.jsp");
		List<Doctor> pt = ddao.getAllDoctor();
		mv.addObject("AllDoctor", pt);
		return mv;
	}

	@RequestMapping("/Login")
	public ModelAndView getLogin(@ModelAttribute Login login,HttpServletRequest request,HttpServletResponse response) 
	{
		ModelAndView mv = new ModelAndView();
		Patient p=null;
		Doctor d=null;
		Receptionist r=null;
		Admin admin=null;
		String type="";
		System.out.println("hello");
		HttpSession httpSession=request.getSession();
		boolean b=ldao.getLogin(login);
		if(b)
		{
				type=login.getType();
				if(type.equals("Patient"))
				{
					p=pdao.getPatient(login);
					System.out.println(p.getName());
					httpSession.setAttribute("Patient", p);
					mv.addObject("patientProfile",p);
					mv.setViewName("PatientProfile.jsp");
				}
				if(type.equals("Doctor"))
				{
					d=ddao.getDoctor(login);
					httpSession.setAttribute("Doctor", d);
					mv.addObject("doctorProfile",d);
					mv.setViewName("DoctorProfile.jsp");
				}
				if(type.equals("Receptionist"))
				{
					r=rdao.getReceptionstOBJ(login);
					mv.addObject("ReceptionistProfile",r);
					System.out.println(r);
					httpSession.setAttribute("Receptionist", r);
					mv.setViewName("receptionistProfile.jsp");
				}
				if(type.equals("admin"))
				{
						admin=adminDao.getAdmin(login);
						mv.addObject("AdminProfile",admin);
						httpSession.setAttribute("Admin", admin);
						mv.setViewName("adminProfile.jsp");
				}
		}
		else
		{
			
			
		}return mv;
	}

	@RequestMapping("/Appointment")
	public ModelAndView appointmentPageShow() {
		ModelAndView mv = new ModelAndView();
		List<Doctor> doctors = ddao.getAllDoctor();
		mv.addObject("Doctors", doctors);
		mv.setViewName("AppointmentBook.jsp");
		return mv;
	}

	@RequestMapping("/AppointmentCreate")
	public ModelAndView appointmentcreate(HttpServletRequest request, HttpServletResponse response) {
		Date date = null;

		ModelAndView mv = new ModelAndView();
		HttpSession httpSession = request.getSession();
		FamailyMember f = (FamailyMember) httpSession.getAttribute("PatientProfile");
		System.out.println( httpSession.getAttribute("PatientProfile"));
		int p_id = f.getP_id();
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("doctorID"));
		int d_id = Integer.parseInt(request.getParameter("doctorID"));

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
		try {
			String sdate = sdf2.format(sdf1.parse(request.getParameter("AppointmentDate")));
			date = df.parse(sdate);
			System.out.println("heee" + date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int time = Integer.parseInt(request.getParameter("AppointmentTime"));

		Appointment ap = new Appointment();

		ap.setAppointment_ID(abdao.getAppointmentID());
		ap.setD_id(d_id);
		ap.setP_id(p_id);
		ap.setDate(date);
		//abdao.getCount(ap);
	/*	System.out.println("Hey" + date.getDate());
		System.out.println("Hey" + date.getMonth());
		System.out.println("Hey year" + date.getYear());*/
		ap.setTime(time);
		System.out.println(request.getParameter("AppointmentDate"));
		System.out.println(ap.getD_id());

		int count = abdao.getCount(ap);
		System.out.println(count);
		if (count > 2) {
			mv.addObject("Count", count);
		} else {
			mv.addObject("Count", count);
			abdao.createAppointment(ap);
			mv.addObject("AppointmentDetail", ap);

		}
		mv.setViewName("AppointentDetails.jsp");

		return mv;
	}
    
	@RequestMapping("/ViewAppointment")
	public ModelAndView getPatientAppointment(HttpServletRequest request,HttpServletResponse response)
	{
		Date date;
		int timeid;
		ModelAndView mv=new ModelAndView();
		//List<Patient> lpList=null;
		HttpSession httpSession=request.getSession();
		Doctor d=(Doctor)httpSession.getAttribute("DoctorProfile");
	//	int count=ddao.getAppointCount(d.getD_Id());
		
	/*	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
		try {
			date=sdf1.parse(request.getParameter("date"));
			timeid=Integer.parseInt(request.getParameter("AppointmentTime"));
			System.out.println(date);*/
		List<Appointment>lAppointments=	abdao. getSpecificDoctorAppointment(request,response);
			System.out.println("All details"+lAppointments.toString());
			
		 /* String sdate = sdf2.format(sdf1.parse(request.getParameter("date")));
			date = df.parse(sdate);
			System.out.println("heee" + date);
		
			} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
			List<FamailyMember> lFamailyMembers=famailyMemberDao.getAllFamilyMember();
			httpSession.setAttribute("AllPatient", lFamailyMembers);
			mv.addObject("AllPatient",lFamailyMembers);
			System.out.println("Total size"+lFamailyMembers.size());
			mv.addObject("ListOfSpecificAppointment",lAppointments);
			mv.setViewName("DoctorAppointmentView.jsp");
		
			
		//mv.addObject("AppointmentCount",count);
		//mv.setViewName("AppointmentCount.jsp");
		return mv;
	}
	
	@RequestMapping("/PatientAppointmentView")
	public ModelAndView getAllPatientAppointmentdetails(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView mv=new ModelAndView();
		HttpSession httpSession=request.getSession();
		List<Appointment> appointment=abdao.getSpecificPatientAppointment(request, response);
		List<Doctor> dList = ddao.getAllDoctor();
		List<Patient> lPatients=pdao.getAllPatient();
		httpSession.setAttribute("AllPatient", lPatients);
		mv.addObject("AllDoctor", dList);
		//mv.addObject("AllPatient",lPatients);
		mv.addObject("AppointmentBookDetils",appointment);
		mv.setViewName("PatientAppointmentView.jsp");
		
		httpSession.setAttribute("AppointmentBookDetils", appointment);
		httpSession.setAttribute("AllDoctor",dList);
		return mv;			
	}
	
	@RequestMapping("/editPatientAppointment")
	public ModelAndView editAppointment(HttpServletRequest request,HttpServletResponse response)
	{
		int appointmentID=Integer.parseInt(request.getParameter("e1"));
		System.out.println("ppppp");
		
		HttpSession httpsession=request.getSession();
		httpsession.setAttribute("EditPatientAppointmentID", appointmentID);
		Appointment appointment=null;
		ModelAndView mv=new ModelAndView();
		if(abdao.iseditable(appointmentID)==true)
		{
		System.out.println("inside if");
		
		appointment=abdao.getAppointmentInformation(appointmentID);
		mv.addObject("AppointmentInformation",appointment);
		List<Doctor> dList = ddao.getAllDoctor();
		
		httpsession.setAttribute("AppointmentInformation", appointment);
		httpsession.setAttribute("AllDoctor",dList);
		mv.setViewName("EditAppointmentInformation.jsp");
		
		}
		else
		{
			System.out.println("inside else");
			mv.addObject("AppointmentInformation",appointment);
			mv.setViewName("PatientAppointmentView.jsp");
		}
		return mv;
	}
	
	@RequestMapping("/updatePatientAppointment")
	public ModelAndView AppointmentUpdate(HttpServletRequest request,HttpServletResponse response)
	{	
		ModelAndView mv=new ModelAndView();
		int d_id=Integer.parseInt(request.getParameter("DoctorID"));
		int time_id=Integer.parseInt(request.getParameter("AppointmentTime"));
		HttpSession httpSession=request.getSession();
		int appointmentID=(Integer)httpSession.getAttribute("EditPatientAppointmentID");
		
		
		//Patient patient=(Patient)request.getAttribute("patientProfile");
		String sdate=request.getParameter("date");
			Date date=null;
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date=sdf1.parse(sdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(date.compareTo(new Date())<0)
			{
				mv.addObject("DateSelectError","Date is already gone");
				mv.setViewName("EditAppointmentInformation.jsp");
			}
			else
			{
				abdao.updatePatientAppointment(appointmentID,d_id,date,time_id);
				List<Appointment> appointment=abdao.getSpecificPatientAppointment(request, response);
				httpSession.setAttribute("AppointmentBookDetils", appointment);
				mv.setViewName("PatientAppointmentView.jsp");
			}
		return mv;
	}
	
	@RequestMapping("/createLeave")
	public ModelAndView CreateLeave(HttpServletRequest request,HttpServletResponse response)
	{	
		int time_id=0;
		ModelAndView mv=new ModelAndView();
		System.out.println("Before");
		HttpSession httpSession=request.getSession();
		Doctor doctor=(Doctor)httpSession.getAttribute("DoctorProfile");
		DoctorLeave leave=new DoctorLeave();
		leave.setLeave_ID(1);
		leave.setD_Id(doctor.getD_Id());
		
		Date Leave_Date =null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
		try {
			String sdate = sdf2.format(sdf1.parse(request.getParameter("Leave_Date")));
			Leave_Date = df.parse(sdate);
			System.out.println("heee" + Leave_Date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		time_id=Integer.parseInt(request.getParameter("LeaveTime"));
		
		leave.setLeave_Date(Leave_Date);
		leave.setLeave_Time(time_id);
		System.out.println(doctor);
		leaveDao.createLeave(leave);
		System.out.println("After");
		mv.addObject("LeaveObject",leave);
		mv.setViewName("ShowAllLeave.jsp");
		
		return mv;
	}
	
	@RequestMapping("/ShowAllLeave")
	public ModelAndView showAllLeave(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=new ModelAndView();
		HttpSession httpSession=request.getSession();
		Doctor doctor=(Doctor)httpSession.getAttribute("DoctorProfile");
		List<DoctorLeave>doctorLeaves=leaveDao.allLeave(doctor.getD_Id());
		httpSession.setAttribute("AllLeave",doctorLeaves);
		modelAndView.addObject("AllLeave", doctorLeaves);
		modelAndView.setViewName("ShowAllLeave.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/editLeaveCheck")
	public ModelAndView editLeave(HttpServletRequest request,HttpServletResponse response)
	{
		
		/*
		 * 
		 * To check how many selection of checkbox
		 * 
		 */
		int leave_id=0;
		HttpSession httpSession=request.getSession();
		ModelAndView modelAndView=new ModelAndView();
		if(request.getParameter("e1")!=null && request.getParameter("d1")!=null)
		{
			modelAndView.setViewName("ShowAllLeave");
			return modelAndView;
			
		}
		if(request.getParameter("e1")!=null)
		{
			leave_id=Integer.parseInt(request.getParameter("e1"));
			httpSession.setAttribute("leave_id", leave_id);
			if(leaveDao.iseditable(leave_id))
			{
				modelAndView.addObject("DateGone","You selected Appointment is Gone");
				modelAndView.setViewName("ShowAllLeave.jsp");
				return modelAndView;
			}
			else
			{
			modelAndView.setViewName("editLeave.jsp");
			return modelAndView;
			}
		}
		else
		{
			leave_id=Integer.parseInt(request.getParameter("d1"));
			if(leaveDao.deleteLeave(leave_id))
			{
			Doctor doctor=(Doctor)httpSession.getAttribute("DoctorProfile");
			List<DoctorLeave>doctorLeaves=leaveDao.allLeave(doctor.getD_Id());
			httpSession.setAttribute("AllLeave",doctorLeaves);
			modelAndView.setViewName("ShowAllLeave.jsp");
			return modelAndView;
			}
			else
			{
				modelAndView.addObject("DateGone","You selected Appointment is Gone");
				modelAndView.setViewName("ShowAllLeave.jsp");
				return modelAndView;
			}
		}
		}
	
	@RequestMapping("/modifyleave")
	public ModelAndView modifyleaveAppointment(HttpServletRequest request,HttpServletResponse response)
	{
		Date toDay=new Date();
		int leave_id=0;
		int time_id=0;
		HttpSession httpSession=request.getSession();
		leave_id=(Integer)httpSession.getAttribute("leave_id");
		ModelAndView modelAndView=new ModelAndView();
		DoctorLeave doctorLeave=leaveDao.getLeave(leave_id);
		
		Date leave_Date =null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
		try 
		{
			String sdate = sdf2.format(sdf1.parse(request.getParameter("Leave_Date")));
			leave_Date = df.parse(sdate);
			System.out.println("heee" + leave_Date);
		}
		catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(toDay.compareTo(leave_Date)>0)
		{
			modelAndView.addObject("dateDone","Plz select proper Date");
			modelAndView.setViewName("editLeave.jsp");
			return modelAndView;
		}
		
		time_id=Integer.parseInt(request.getParameter("Leave_Time"));
		doctorLeave.setLeave_Date(leave_Date);	
		doctorLeave.setLeave_Time(time_id);
		
		
		leaveDao.updateLeave(doctorLeave);
		
		Doctor doctor=(Doctor)httpSession.getAttribute("DoctorProfile");
		List<DoctorLeave>doctorLeaves=leaveDao.allLeave(doctor.getD_Id());
		httpSession.setAttribute("AllLeave",doctorLeaves);
		
		modelAndView.setViewName("ShowAllLeave.jsp");
		return modelAndView;
	}
	
	@RequestMapping("Today_Patient")
	public ModelAndView Today_Patient(HttpServletRequest request,HttpServletResponse response)
	{
		
		HttpSession httpSession=request.getSession();
		
		ModelAndView modelAndView=new ModelAndView();
		Date date=new Date();
		
		System.out.println(date.getHours());
		System.out.println(date.getMinutes());
		Doctor doctor=(Doctor)httpSession.getAttribute("DoctorProfile");
		
		List<Appointment> lAppointments=	abdao.currentPatientAppointment(doctor.getD_Id());
		List<FamailyMember> lFamailyMembers=famailyMemberDao.getTodayPatientID(lAppointments);
		int h=date.getHours();	
		modelAndView.addObject("PatientAttend", lAppointments);
		modelAndView.addObject("Patients",lFamailyMembers);
		
		httpSession.setAttribute("PatientAttend", lAppointments);
		httpSession.setAttribute("Patients", lFamailyMembers);
		modelAndView.setViewName("PatientAttend.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/Treat_Patient")
	public ModelAndView treat_Patient(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=new ModelAndView();
		HttpSession httpSession=request.getSession();
		Doctor doctor=(Doctor)httpSession.getAttribute("DoctorProfile");
		
		//int patient_id=Integer.parseInt(request.getParameter("r1"));
		
		int appointment_id=Integer.parseInt(request.getParameter("r1"));
		httpSession.setAttribute("AppointmentID", appointment_id);
		
		Appointment appointment=abdao.getAppointmentInformation(appointment_id);
		
		List<Appointment> appointmentHistory=abdao.patientAppointmentHistory(appointment.getP_id(), doctor.getD_Id());
		
		FamailyMember famailyMember=famailyMemberDao.geFamailyMember(appointment.getP_id());
		modelAndView.addObject("PatientHistory",appointmentHistory);
		modelAndView.addObject("Patient",famailyMember);
		modelAndView.setViewName("TreatPatient.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/UpdateTreatMent")
	public ModelAndView UpdateTreatMent(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=new ModelAndView();
		HttpSession httpSession=request.getSession();
		
		
		
		
		int appointmentID=(Integer)httpSession.getAttribute("AppointmentID");
		Appointment appointment=abdao.getAppointmentInformation(appointmentID);
		appointment.setDiscription(request.getParameter("Discription"));
		appointment.setMedicine(request.getParameter("Medicine"));
		appointment.setPayment(Integer.parseInt(request.getParameter("payment")));
		appointment.setPayment_type(request.getParameter("payment_type"));
		
		abdao.updateTreatement(appointment);
		
		Today_Patient(request, response);
		modelAndView.setViewName("PatientAttend.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/AppointmentManagement")
	public ModelAndView ReceptionistManagement(HttpServletRequest request,HttpServletResponse response)
	{
		
		String sdate=null;
		Date date=null;
		int time_id=0;
		HttpSession httpSession=request.getSession();
		ModelAndView modelAndView=new ModelAndView();
			if(httpSession.getAttribute("Date1")==null)
			{
				
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
					try 
					{
							sdate = sdf2.format(sdf1.parse(request.getParameter("ManagementDate")));
							time_id=Integer.parseInt(request.getParameter("ManagementTime"));
							httpSession.setAttribute("pdate", sdate);
							httpSession.setAttribute("ptime", time_id);
							date = df.parse(sdate);
					}
					catch (ParseException e1) 	
					{
							e1.printStackTrace();
					}
				
				
				time_id=Integer.parseInt(request.getParameter("ManagementTime"));
				
				httpSession.setAttribute("timeId1", time_id);
				httpSession.setAttribute("Date1", sdate);
				System.out.println("Inside IF");
			}
			else
			{
						
						sdate=(String)httpSession.getAttribute("pdate");
						
						time_id=(Integer)httpSession.getAttribute("ptime");
						System.out.println("Inside Else");
						System.out.println(sdate);
						System.out.println(time_id);
		
			}
		List<Appointment> lAppointments=abdao.getAllAppointmentViewOfSelectedDate(sdate, time_id);
		//System.out.println(lAppointments);
		List<DoctorLeave> doctorLeaves=leaveDao.getAllLeave(sdate,time_id);
		//System.out.println(doctorLeaves);
		List<FamailyMember>lPatients=famailyMemberDao.getAppointmentPatient(lAppointments);
		//System.out.println("Printing");
		System.out.println(lPatients);
		List<Doctor> lDoctors=ddao.getDoctors(lAppointments);
	//	System.out.println(lDoctors);
		//System.out.println(lPatients);
		
		
		modelAndView.addObject("AllAppointment",lAppointments);
		modelAndView.addObject("AllrespectivePatient",lPatients);
		modelAndView.addObject("DoctorLeave",doctorLeaves);
		modelAndView.addObject("Doctors",lDoctors);
		modelAndView.setViewName("ViewPatientAppointment.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/editReceptionistManagement")
	public ModelAndView editReceptionistManagement(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=new ModelAndView();
		int time_id=0;
		String sdate=null;
		HttpSession httpSession=request.getSession();
		if(request.getParameter("e1")!=null && request.getParameter("d1")!=null)
		{
			modelAndView.setViewName("ReceptionistViewAppointment.jsp");
			String pdate=(String)httpSession.getAttribute("pdate");
			System.out.println("from e1 and d1"+pdate);
			
			time_id=(Integer)httpSession.getAttribute("ptime");
			httpSession.setAttribute("Date1", pdate);
			httpSession.setAttribute("ptime", time_id);
			modelAndView.addObject("SelectOne","select only one option");
			modelAndView=ReceptionistManagement(request, response);
			return modelAndView;
		}
		if(request.getParameter("e1")!=null)
		{
			System.out.println("before App");
			int appointmentID=Integer.parseInt(request.getParameter("e1"));
			System.out.println("AppID"+appointmentID);
			httpSession.setAttribute("AppointmentID", appointmentID);
			/*sdate=(String)httpSession.getAttribute("Date1");
				time_id=(Integer)httpSession.getAttribute("timeId1");
			 */			
			
			
			
			Appointment appointment=abdao.getAppointmentInformation(appointmentID);
			FamailyMember patient=famailyMemberDao.getMember(appointment.getP_id());
			Doctor doctor=ddao.getDoctor(appointment.getD_id());					
			List<DoctorLeave> leaves=leaveDao.allLeave(appointment.getD_id());
			
			httpSession.setAttribute("DoctorId",appointment.getD_id());
			httpSession.setAttribute("PatientID", patient.getP_id());
			httpSession.setAttribute("AppointmentID", appointmentID);
			httpSession.setAttribute("DoctorLeave",leaves);
			
			httpSession.setAttribute("Appointment",appointment);
			httpSession.setAttribute("Patient",patient);
			httpSession.setAttribute("Doctor",doctor);
			httpSession.setAttribute("DoctorLeave",leaves);
			modelAndView.setViewName("EditReceptionistAppointment.jsp");
		}
		if(request.getParameter("d1")!=null)
		{
			int appointmentID=Integer.parseInt(request.getParameter("d1"));
			Appointment appointment=abdao.getAppointmentInformation(appointmentID);
			String pdate=(String)httpSession.getAttribute("pdate");
			time_id=(Integer)httpSession.getAttribute("ptime");
			httpSession.setAttribute("Date1", pdate);
			httpSession.setAttribute("ptime", time_id);
			abdao.deleteAppointment(appointment);
			modelAndView=ReceptionistManagement(request, response);
		}
		
		if(request.getParameter("e1")==null && request.getParameter("d1")==null)
		{
			modelAndView.setViewName("ReceptionistViewAppointment.jsp");
			String pdate=(String)httpSession.getAttribute("pdate");
			System.out.println("from e1 and d1"+pdate);
			
			time_id=(Integer)httpSession.getAttribute("ptime");
			httpSession.setAttribute("Date1", pdate);
			httpSession.setAttribute("ptime", time_id);
			modelAndView=ReceptionistManagement(request, response);
			
			
			return modelAndView;
		}
		
		
		return modelAndView;
	}

	@RequestMapping("/updateReceptionistAppointment")
	public ModelAndView updateReceptionistAppointment(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=new ModelAndView();
		HttpSession httpSession=request.getSession();
		int d_id=(Integer)httpSession.getAttribute("DoctorId");
		int appointment_id=(Integer)httpSession.getAttribute("AppointmentID");
		Doctor doctor=ddao.getDoctor(d_id);
		Appointment ap=abdao.getAppointmentInformation(appointment_id);
		
		Date date=null;
		String sdate1=request.getParameter("date");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
		try {
			String sdate = sdf2.format(sdf1.parse(sdate1));
			date = df.parse(sdate);
			System.out.println("heee" + date);
		} catch (ParseException e1) {
		
			e1.printStackTrace();
		}
		ap.setDate(date);
		ap.setTime(Integer.parseInt(request.getParameter("editRecpAppTime")));
		boolean b=abdao.updateReceptionistAppointment(ap);
		if(b==false)
		{
			modelAndView.addObject("DateError","Plz select proper Date");
			modelAndView.setViewName("EditReceptionistAppointment.jsp");
			return modelAndView;
		}
		else
		{
			modelAndView.addObject("AppointmentUpdated","Appointment Updated Successfully");
			modelAndView.setViewName("EditReceptionistAppointment.jsp");
			return modelAndView;
		}
		//modelAndView=editReceptionistManagement(request, response);
		//List<DoctorLeave> leaves=leaveDao.allLeave(appointment.getD_id());
		
	}
	
	@RequestMapping("/BackAfterUpdate")
	public ModelAndView BackAfterUpdate(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession httpSession=request.getSession();
		int time_id=0;
		ModelAndView modelAndView=new ModelAndView();
		String pdate=(String)httpSession.getAttribute("pdate");
		time_id=(Integer)httpSession.getAttribute("ptime");
		httpSession.setAttribute("Date1", pdate);
		httpSession.setAttribute("ptime", time_id);
		modelAndView=ReceptionistManagement(request, response);
		
		return modelAndView;
	}
	
	@RequestMapping("/ShowAllDoctorS")
	public ModelAndView showAllDoctorS() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("AdminDoctorView.jsp");
		List<Doctor> pt = ddao.getAllDoctor();
		mv.addObject("AllDoctor", pt);
		return mv;
	}

	@RequestMapping("/AdminViewLeave")
	public ModelAndView showLeave(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=new ModelAndView();
		int d_id=Integer.parseInt(request.getParameter("r1"));
		List<DoctorLeave> lDoctorLeaves=leaveDao.allLeave(d_id);
		Doctor d=ddao.getDoctor(d_id);
		modelAndView.addObject("DoctorName",d.getD_Name());
		modelAndView.addObject("AllLeave",lDoctorLeaves);
		modelAndView.setViewName("viewDoctorLeave.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/AdminViewPatientdetails")
	public ModelAndView AdminViewPatientdetails(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=new ModelAndView();
		int p_id=Integer.parseInt(request.getParameter("p1"));
		List<Appointment> lAppointments=abdao.numberOfVisit(p_id);
		modelAndView.addObject("PatientAllAppointment",lAppointments);
		modelAndView.addObject("DoctorDao",ddao);
		modelAndView.setViewName("AdminViewPatientDetails.jsp");
		return modelAndView;
	}
	@RequestMapping("/ReceptionistCreateAppointment")
	public ModelAndView ReceptionistCreateAppointment(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=new ModelAndView();
		List<Doctor> doctors = ddao.getAllDoctor();
		modelAndView.addObject("Doctors", doctors);
		modelAndView.setViewName("ReceptionistBookAppointment.jsp");
		System.out.println("hhhhhh");
		return modelAndView;
	}
	
	@RequestMapping("/ReceptionAppointBook")
	public ModelAndView ReceptionAppointBook(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=new ModelAndView();
		int appointmentid=urgantAppointmentBookDao.getUrgentAppointmentId()+1;
		UrgantAppointmentBook urgantAppointmentBook=new UrgantAppointmentBook();
		urgantAppointmentBook.setAppointment_ID(appointmentid);
		urgantAppointmentBook.setP_id("U"+appointmentid);
		String sdate=request.getParameter("AppointmentDate");
		Date date=null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
		try {
			 sdate = sdf2.format(sdf1.parse(sdate));
			date = df.parse(sdate);
			System.out.println("heee" + date);
		} catch (ParseException e1) {
		
			e1.printStackTrace();
		}
	urgantAppointmentBook.setDate(date);
	urgantAppointmentBook.setTime(Integer.parseInt(request.getParameter("AppointmentTime")));
	return modelAndView;
	}
	
	@RequestMapping("/addfamilyMember")
	public ModelAndView addfamilyMember(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=new ModelAndView();
		HttpSession httpSession=request.getSession();
		Patient p=(Patient)httpSession.getAttribute("Patient");
		int fid=p.getP_id();
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		String gender=request.getParameter("gender");
		FamailyMember famailyMember=new FamailyMember();
		famailyMember.setFamilyId(fid);
		famailyMember.setName(name);
		famailyMember.setAge(age);
		famailyMember.setGender(gender);
		famailyMemberDao.addFamilyMember(famailyMember);
		modelAndView.setViewName("PatientProfile.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/ViewMember")
	public ModelAndView ViewMember(HttpServletRequest request,HttpServletResponse response)
	{
		ModelAndView modelAndView=new ModelAndView();
		HttpSession httpSession=request.getSession();
		Patient p=(Patient)httpSession.getAttribute("Patient");
		int fid=p.getP_id();
		List<FamailyMember>famailyMembers=famailyMemberDao.getAllFamilyMember(fid);
		modelAndView.addObject("FamilyMember",famailyMembers);
		modelAndView.setViewName("AllFamilyMember.jsp");
		
		return modelAndView;
	}
	
	@RequestMapping("/ViewSelectMemberDetails")
	public ModelAndView ViewSelectMemberDetails(HttpServletRequest request,HttpServletResponse response)
	{
		
		ModelAndView modelAndView=new ModelAndView();
		HttpSession httpSession=request.getSession();
		Patient p=(Patient)httpSession.getAttribute("Patient");
		//int fid=p.getP_id();
		int p_id=Integer.parseInt(request.getParameter("e1"));
		
		FamailyMember famailyMember=famailyMemberDao.getMember(p_id);
		httpSession.setAttribute("PatientProfile",famailyMember);
		modelAndView.addObject("Member",famailyMember);
		modelAndView.setViewName("ViewSelectMemberDetails.jsp");
		
		return modelAndView;
	}
	
}
