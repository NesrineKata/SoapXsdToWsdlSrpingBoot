package tekup.de.soap;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import de.tekup.soap.models.whitetest.Address;
import de.tekup.soap.models.whitetest.Student;

@Component
public class StudentRepository {
	
	private static final Map<Integer, Student> students = new HashMap<>();
	 
    @PostConstruct
    public void initData() {
 
    	Address ad=new Address();
    	ad.setCity("Tunis");
    	ad.setPosteCode(2090);
    	ad.setStreet("Bacha");
        Student std = new Student();
        std.setId(1);
        std.setName("Nesrine");
        std.setAddress(ad);
        students.put(std.getId(),std);
    	Address ad2=new Address();
    	ad2.setCity("Sousse");
    	ad2.setPosteCode(2099);
    	ad2.setStreet("Roma");
        Student sen  = new Student();
        sen.setId(2);
        sen.setName("Ali");
        sen.setAddress(ad2);
        students.put(sen.getId(),sen);
    	Address ad3=new Address();
    	ad3.setCity("Sfax");
    	ad3.setPosteCode(3909);
    	ad3.setStreet("farhat hached");
        Student ali = new Student();
        ali.setId(3);
        ali.setName("Ali");
        ali.setAddress(ad3);
        students.put(ali.getId(),ali);
 
        
    }
 
    public boolean existStudentById(int id) {
        Assert.notNull(id, "The student's id must not be null");
        return students.containsKey(id);
    }
    public Student findStudentById(int id) {
        Assert.notNull(id, "The student's id must not be null");
        return students.get(id);
    }
    
}
