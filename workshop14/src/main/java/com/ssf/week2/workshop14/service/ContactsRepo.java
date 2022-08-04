package com.ssf.week2.workshop14.service;

import com.ssf.week2.workshop14.model.Contact;

public interface ContactsRepo {
    public void save(final Contact ctc);
    public Contact findById(final String contactId);
}
