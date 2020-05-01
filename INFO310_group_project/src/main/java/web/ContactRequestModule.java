package web;

/**
 * INFO310
 * ContactRequestModule.java
 * 
 * Specifies URIs to call a particular DAO method in the ContactRequestDAO.
 * 
 */

import dao.ContactRequestInterface;
import dao.ContactRequestDAO;
import org.jooby.Jooby;
import domain.ContactRequest;

public class ContactRequestModule extends Jooby {
    
    // Creates new interface using the StudentDAO file
    ContactRequestInterface crDAO = new ContactRequestDAO();

    public ContactRequestModule(ContactRequestInterface crDAO) {
        port(8080);
        
        // Saves a newly made contact request in the cr db
        post("/api/contactrequest/newreq", (req, rsp) -> {
            ContactRequest cr = req.body().to(ContactRequest.class);
            crDAO.saveContactRequest(cr);
        });
        
        // Returns a ContactRequest object based on a staff member's id
        get("/api/contactrequest/:staffID", (req) -> {
            String staffID = req.param("staffID").value();
            return crDAO.getRequestByStaffID(staffID);
        });      
    }
}
