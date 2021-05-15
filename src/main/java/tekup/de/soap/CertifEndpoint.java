package tekup.de.soap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import de.tekup.soap.models.whitetest.*;
@Endpoint
public class CertifEndpoint {
	
	
	public static final String nameSpace = "http://www.tekup.de/soap/models/whitetest";
	@Autowired
	private ExamRepository examRepo;
	@Autowired
	private StudentRepository studentRepo;
	
	
	@PayloadRoot(namespace = nameSpace, localPart = "StudentRequest")
	@ResponsePayload
    //public  JAXBElement<WhiteTestResponse> checkStudentEligebilty(@RequestPayload JAXBElement< StudentRequest >   request)  {
	//	JAXBElement<WhiteTestResponse> res =  new JAXBElement<WhiteTestResponse>(new QName(WhiteTestResponse.class.getSimpleName()), WhiteTestResponse.class, null);
	public	WhiteTestResponse checkStudentEligebilty(@RequestPayload StudentRequest request )  {
		WhiteTestResponse response = new WhiteTestResponse();
		List<String> criteriaMismatchs = response.getCriteriaMismatch();
        if(studentRepo.existStudentById(request/*.getValue()*/.getStudentId())==false) 
        	criteriaMismatchs.add("Student id doesn't exist");
    	if(examRepo.existExamById(request/*.getValue()*/.getExamCode())==false) 
    		criteriaMismatchs.add("Exam code doesn't exist");
        if(criteriaMismatchs.isEmpty()) {
			response.setIsEligeble(true);
			Student s=studentRepo.findStudentById(request/*.getValue()*/.getStudentId());
    		Exam e=examRepo.findExamById(request/*.getValue()*/.getExamCode());
    		response.setStudent(s);
    		response.setExam(e);
    		LocalDate localDate = LocalDate.now().plusDays(7);

    		XMLGregorianCalendar now = null;
    		try {
    			DatatypeFactory dataFactory = DatatypeFactory.newInstance();
    			now = dataFactory.newXMLGregorianCalendar(new GregorianCalendar());
    		} catch (DatatypeConfigurationException ex) {
    			System.err.println("unable to construct the date/time object");
    			ex.printStackTrace();
    		}
    		
           
    		response.setDate(now);
		} else {
			response.setIsEligeble(false);	
		}
        		
       //res.setValue(response);
       return response;
    }
	@PayloadRoot(namespace = nameSpace, localPart = "GetExamsRequest")
	@ResponsePayload
   // public  JAXBElement<GetExamsResponse> listOfExams(@RequestPayload JAXBElement< GetExamsRequest >   request)  {
//		JAXBElement<GetExamsResponse> res =  new JAXBElement<GetExamsResponse>(new QName(GetExamsResponse.class.getSimpleName()), GetExamsResponse.class, null);
	public GetExamsResponse listOfExams(@RequestPayload  GetExamsRequest request) {
	GetExamsResponse response = new GetExamsResponse();
		List<Exam> l=examRepo.findAll();
		response.setExams(l);
	//	res.setValue(response);
		return response;
	}
	
}
