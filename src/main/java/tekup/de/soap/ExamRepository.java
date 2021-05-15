package tekup.de.soap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import de.tekup.soap.models.whitetest.*;

@Component
public class ExamRepository {
	
	private static final Map<String, Exam> exams = new HashMap<>();
	 
    @PostConstruct
    public void initData() {
 
        Exam std = new Exam();
        std.setCode("101");
        std.setName("OCA JAVA");
        exams.put(std.getCode(),std);
        Exam aws = new Exam();
        aws.setCode("202");
        aws.setName("Aws practitioner");
       exams.put(aws.getCode(),aws);
        Exam awss = new Exam();
        awss.setCode("203");
        awss.setName("Aws solution Architect");
        exams.put(awss.getCode(),awss);
 
        
    }
    public boolean existExamById(String id) {
        Assert.notNull(id, "The Exam's id must not be null");
        return exams.containsKey(id);
    }
    public Exam findExamById(String id) {
        Assert.notNull(id, "The Exam's id must not be null");
        return exams.get(id);
    }
    public List<Exam> findAll(){
    	List<Exam> l=new ArrayList();
    	 for (Map.Entry<String,Exam> entry : exams.entrySet())
                  l.add(entry.getValue());
    	return l;
    }
    
}

