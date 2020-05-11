package com.nikipon.ipanalysis.web;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nikipon.ipanalysis.domain.Ipdata;
import com.nikipon.ipanalysis.domain.IpdataRepository;

@Controller
public class IpAnalysisController {
	@Autowired
	private IpdataRepository iprepo;
	
	// Display the warning page
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	// display the index page with the options to login or register
	@GetMapping("/index")
	public String index() {
		return "index";
	}	
    
	// login page
	@GetMapping("/login")
    public String login() {	
        return "login";
    }
	
	// the prompt to start collecting user data
	@GetMapping("/start")
	public String start() {
		return "start";
	}	
	
	// admin panel with all of the ip data
    @PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin")
	public String adminView(Model model) {
		
		model.addAttribute("data", iprepo.findAll());
		return "admin";
	}
	
	// on redirect, collect user ip-data
	@GetMapping("/data")
	public String data(HttpServletRequest request, Model model) {
		String user = request.getRemoteUser();
		
		int port = request.getRemotePort();
		String userAgent = request.getHeader("User-Agent");
		String ip = request.getHeader("X-FORWARDED-FOR");  
		if (ip == null) {  
		    ip = request.getRemoteAddr();  
		}
		
		ArrayList<String> ispAndLocData = grabIpData(ip);
		String location = ispAndLocData.get(1); 
		String	isp = ispAndLocData.get(0);
		
		Ipdata ipdata = new Ipdata(ip, location, isp, userAgent, port, user);
		iprepo.save(ipdata);
		
		model.addAttribute("data", ipdata);
		return "data";
	}
	
	
	// Made according to https://github.com/jusju/botti/blob/master/src/main/java/fi/haagahelia/botengine/MyAmazingBot.java
	
	public ArrayList<String> grabIpData(String ip) {
		String inline = "";
		ArrayList<String> data = new ArrayList<String>();
		
		try {
		URL url = new URL( 
				"https://www.iplocate.io/api/lookup/" + ip);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		int responsecode = con.getResponseCode();
		
		if (responsecode != 200) {
			throw new RuntimeException("HttpResponseCode: " + responsecode);
		} 
		else {
			Scanner sc = new Scanner(url.openStream());
			
			while (sc.hasNext()) {
				inline += sc.nextLine();
			}
		sc.close();
		System.out.println("\nJSON data in string format");
		System.out.println(inline);
		JSONParser parse = new JSONParser();
		
		JSONObject result = (JSONObject) parse.parse(inline);
		
		String isp = (String) result.get("org");
		String location = (String) result.get("city")+ ", " + (String) result.get("country");
		
		data.add(isp);
		data.add(location);
			
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	

	
	
}
