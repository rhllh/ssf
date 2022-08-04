package com.ssf.week1.workshop13.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.ssf.week1.workshop13.model.Contact;

@Component("contacts")
public class Contacts {
    private static final Logger logger = LoggerFactory.getLogger(Contacts.class);

    public Contacts() {

    }

    public void saveContact(Contact contact, ApplicationArguments args) {
        List<String> optsVals = args.getOptionValues("dataDir");
        String dataDir = optsVals.get(0);
        logger.info("CONTACTS: dataDir >> " + dataDir);

        String id = contact.getId();

        File f = new File(dataDir + File.separator + id);
        try {
            OutputStream os = new FileOutputStream(f);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(contact.getId() + "\n");
            bw.write(contact.getName() + "\n");
            bw.write(contact.getEmail() + "\n");
            bw.write(contact.getPhone());

            bw.flush();
            osw.flush();
            os.flush();
            bw.close();
            osw.close();
            os.close();
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
    }

    public Contact getById(String id, ApplicationArguments args) {
        List<String> optsVals = args.getOptionValues("dataDir");
        String dataDir = optsVals.get(0);
        logger.info("CONTACTS: dataDir >> " + dataDir);

        Contact contact = new Contact();
        Path path = new File(dataDir + File.separator + id).toPath();
        try {
            Charset charset = Charset.forName("UTF-8");
            List<String> lines = Files.readAllLines(path,charset);
            contact.setId(id);
            contact.setName(lines.get(1));
            contact.setEmail(lines.get(2));
            contact.setPhone(lines.get(3));
        } catch (IOException e) {
            logger.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact ID not found");
        }

        return contact;
    }
}
