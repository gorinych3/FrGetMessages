package ru.gorinych3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.gorinych3.MyProp;
import ru.gorinych3.mail.EmailServiceImpl;
import ru.gorinych3.models.EntityWrapper;
import ru.gorinych3.models.FrMessage;
import ru.gorinych3.models.MessagesLSP;
import ru.gorinych3.repositories.LSPRepositoryCustomImp;
import ru.gorinych3.repositories.MessRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class FrService {

    @Autowired
    private MessRepository repository;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private LSPRepositoryCustomImp repositoryCustom;

    @Autowired
    private MyProp property;

    /**
     * com.microsoft.sqlserver.jdbc.SQLServerException: Недопустимое имя столбца InsertDate
     */
    @Transactional
    public void getAllNMessages(){

        System.out.println("FrService - getAllNMessages started!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        List<MessagesLSP> messages =  repositoryCustom.getMessagesLSP();
        System.out.println("FrService - getAllNMessages GET MESSAGES!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        if(messages != null) {
            for (MessagesLSP mess : messages) {
                //mess.setCount(1);
                System.out.println("Object from bd - " + mess.toString());

            }

        } else System.out.println("СПИСОК ПУСТОЙ!!!!!!!!!!!!!!!!!!!");


    }


    @Transactional
    public void newMethod(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss:S");
        System.out.println("стартовое время = " + dateFormat.format(new Date()));
        Set<MessagesLSP> mess = repository.findMessagesLSPSByStatus("N");
        if(mess != null) {
            for (MessagesLSP mes : mess) {
             mes.setStatus("W");
             mes.setLastProcessing(new Date());
             repository.save(mes);
             //FrMessage frMessage = parseEntityToPojo(mes);
             //frMessage.setCount(1);
                putEntityToWrapper(mes);
            }
            System.out.println("финишное время = " + dateFormat.format(new Date()));
            /*
            for (MessagesLSP messagesLSP : mess){
                messagesLSP.setStatus("N");
                repository.save(messagesLSP);
//                if(messagesLSP.getId()%1000==0){
//                    System.out.println(messagesLSP.toString());
//                }
            }
*/

        } else System.out.println("СПИСОК ПУСТОЙ!!!!!!!!!!!!!!!!!!!");
        //emailService.sendSimpleMessage(property.getReciever(), "test", "Пробное сообщение");
    }

    private FrMessage parseEntityToPojo(MessagesLSP lsp){
        return checkMessage(new FrMessage(lsp.getId(), lsp.getLastProcessing(), lsp.getNumber(), lsp.getText(), 1));
    }

    private void putEntityToWrapper(MessagesLSP lsp){
        checkMessage(new EntityWrapper(lsp, 1));
    }

    private void myQueue(FrMessage frMessage){
        //repository.updateStatusLastProcessing("E", new Date(), frMessage.getId());

    }

    private FrMessage checkMessage(FrMessage frMessage){
        if(frMessage.getText().contains("Идейные соображения высшего порядка")){
            frMessage.setCount(frMessage.getCount() + 1);
            myQueue(frMessage);
        }
        return frMessage;
    }

    private void checkMessage(EntityWrapper entityWrapper){
        if(entityWrapper.getEntity().getText().contains("Идейные соображения высшего порядка")){
            entityWrapper.setCount(entityWrapper.getCount() + 1);
            entityWrapper.getEntity().setStatus("E");
           repository.save(entityWrapper.getEntity());
            //System.out.println(entityWrapper.getCount());
        }
        //else System.out.println(postTextForObject(entityWrapper.getEntity().getText()));
        else postTextForObject(entityWrapper.getEntity().getText());

    }

    private Integer postTextForObject(String text) {
        RestTemplate rest = new RestTemplate();
        //System.out.println(text);
        return rest.postForObject("http://localhost:8082/connect",
                text, Integer.class);
    }

}
