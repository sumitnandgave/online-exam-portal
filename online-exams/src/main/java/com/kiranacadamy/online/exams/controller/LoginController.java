package com.kiranacadamy.online.exams.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kiranacadamy.online.exams.entity.Answer;
import com.kiranacadamy.online.exams.entity.Questions;
import com.kiranacadamy.online.exams.entity.User;

@Controller
public class LoginController {


@Autowired
	
	SessionFactory factory;

	// localhost:8080/login
	@RequestMapping("login")
	public String login()
	{
		return "login";  // here login is jsp page name
	}
	
	
	@RequestMapping("validate")
	public ModelAndView validate(String username,String password)
	{
		ModelAndView modelandview=new ModelAndView();
		
		org.hibernate.Session session=factory.openSession();

		User userfromdb=session.get(User.class,username);
			
		if(userfromdb==null)
		{
			modelandview.setViewName("login"); // view page name means JSP page name
			modelandview.addObject("message","wrong username"); // here message attribute represents data which will be displayed on view page . here message is called model attribute
			
		}
		
		else if(userfromdb.password.equals(password))
		{
			modelandview.setViewName("welcome"); // view page name means JSP page name
			
		}
		
		else
		{
			modelandview.setViewName("login"); // view page name means JSP page name
			modelandview.addObject("message","wrong password"); // here message attribute represents data which will be displayed on view page . here message is called model attribute
		}
		
		return modelandview;
	
	
	}
	
	
	
	
	@RequestMapping("startExam")
	public ModelAndView startExam(String selectedSubject,HttpServletRequest request)
	{
		System.out.println(selectedSubject);
	
		ModelAndView modelAndView=new ModelAndView();
		
		if(selectedSubject==null)
		{
			modelAndView.setViewName("login");
		}
		else
		{
		org.hibernate.Session session=factory.openSession();
		
		/* Criteria is for only fetching records ( select query)
		 * HQL is for all operations (insert , update,delete,select )
		 *  */
		// using add() we add condition , based on which records are fetched from database
		// e.g. we want only those records which are having value maths for subject column ( assume selectedsubject is maths)
		// select * from questions where subject='maths'
		//List listOfQuestions=session.createCriteria(Questions.class).add(Restrictions.eq("subject",selectedSubject)).list();
		
		HttpSession httpsession=request.getSession();//httpsession==> [ qno=0 , score=0 setAttribute() ] HttpSession object
		httpsession.setAttribute("qno",0);
		
		Query query=session.createQuery("from Questions where subject=:subject");
		query.setParameter("subject",selectedSubject);
		List<Questions> listOfQuestions=query.list();
		
		
		modelAndView.setViewName("questions");
		
		modelAndView.addObject("question",listOfQuestions.get(0));
		httpsession.setAttribute("allquestions",listOfQuestions);
	
		HashMap<Integer,Answer> hashmap=new HashMap<Integer, Answer>();
		httpsession.setAttribute("submittedDetails",hashmap);
		
		httpsession.setAttribute("score",0);
		httpsession.setAttribute("subject",selectedSubject);
		}
		return modelAndView;
	
	}
	


	}
	




 
	

