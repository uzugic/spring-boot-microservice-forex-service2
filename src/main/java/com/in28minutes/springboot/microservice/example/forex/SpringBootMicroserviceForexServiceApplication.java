package com.in28minutes.springboot.microservice.example.forex;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@SpringBootApplication
public class SpringBootMicroserviceForexServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroserviceForexServiceApplication.class, args);
	}
	
	
	  
	/*  @Autowired
	  private ExchangeValueRepository repository;
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() throws IOException {
		
		
		Document doc = Jsoup.connect("https://www.nbs.rs/kursnaListaModul/srednjiKurs.faces?lang=lat").get();

		Element table = doc.getElementById("index:srednjiKursLista");

	    ArrayList<Integer> idValute = new ArrayList<>();	
		ArrayList<String> oznakaValute = new ArrayList<>();
		ArrayList<Integer> vaziZa = new ArrayList<>();
		ArrayList<Double> srednjiKurs = new ArrayList<>();

			
		int size = table.select("tr").size();
		for(int i=1; i<size; i++) {
			idValute.add(Integer.parseInt(table.select("tr").get(i).select("td").get(0).text()));
			oznakaValute.add(table.select("tr").get(i).select("td").get(2).text());
			vaziZa.add(Integer.parseInt(table.select("tr").get(i).select("td").get(3).text()));
			srednjiKurs.add(Double.parseDouble(table.select("tr").get(i).select("td").get(4).text()));
		}

		String content = "";

		for(int i=0;i<srednjiKurs.size();i++) {
		
			long id = idValute.get(i);
			int unit = vaziZa.get(i);
		  //  BigDecimal bigD =  new BigDecimal(srednjiKurs.get(i)).setScale(4, BigDecimal.ROUND_HALF_UP);
		    BigDecimal bigD =  new BigDecimal(srednjiKurs.get(i));
		    ExchangeValue exchangeValue = new ExchangeValue(id ,oznakaValute.get(i),"RSD",unit,bigD);
		   System.out.println(bigD);
		   System.out.println(exchangeValue.getConversionMultiple());
		    repository.save(exchangeValue);
			
		}
		
		
	

        }*/
	}



