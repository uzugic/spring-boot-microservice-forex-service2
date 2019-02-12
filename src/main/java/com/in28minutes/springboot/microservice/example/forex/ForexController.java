package com.in28minutes.springboot.microservice.example.forex;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@RestController
public class ForexController {
  
  @Autowired
  private Environment environment;
  
  @Autowired
  private ExchangeValueRepository repository;
  
 
  
  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public ExchangeValue retrieveExchangeValue
    (@PathVariable String from, @PathVariable String to){
    
    ExchangeValue exchangeValue = 
        repository.findByFromAndTo(from, to);
    
    
    exchangeValue.setPort(
        Integer.parseInt(environment.getProperty("local.server.port")));
    
    
    System.out.println(exchangeValue.getConversionMultiple());
    
    return exchangeValue;
  }
  
  @GetMapping("/currency-exchange/getList")
  public  List<ExchangeValue> retrieveExchangeList(){
    
    List<ExchangeValue> exchangeValues = 
        repository.findAll();

    
    
    System.out.println(exchangeValues.toString());
    
    return exchangeValues;
  }
  
  
  @GetMapping("/currency-exchange/fetchList")
  public  void fetchList() throws IOException{
    
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
		
    
 
  }
  
}