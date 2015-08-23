package hu.neuron.java.core.util;

import hu.neuron.java.core.dao.CountDAO;
import hu.neuron.java.core.entity.Count;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator {
	
	@Autowired
	CountDAO countDao;
	
	private static List<Count> counter = new LinkedList<>();
	
	public String generate(String type){
		if(counter.size() == 0){
			counter = countDao.findAll();
		}
		GregorianCalendar current = new GregorianCalendar(); 
		int currentDay = current.get(Calendar.DAY_OF_MONTH);
		Count wanted = null;
		for(Count c : counter){
			if(c.getType().equals(type)){
				wanted = c;
			}
		}
		if(wanted == null){
			wanted = new Count();
			wanted.setDayOfMonth(currentDay);
			wanted.setType(type);
			wanted.setCount(1L);
			counter.add(wanted);
			countDao.save(wanted);
		}
		else{
			if(wanted.getDayOfMonth() != currentDay){
				wanted.setDayOfMonth(currentDay);
				wanted.setCount(0L);
			}
			wanted.setCount(wanted.getCount()+1);
			countDao.save(wanted);
		}
		int currentYear = current.get(Calendar.YEAR);
		int currentMonth = current.get(Calendar.MONTH)+1;
		return type.toUpperCase() + "/" + currentYear + "-" + currentMonth + "-" + currentDay + "/" +
				wanted.getCount();
	}

}
