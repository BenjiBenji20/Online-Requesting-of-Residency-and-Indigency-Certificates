package com.azathoth.OLRResidency_Indigency.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendSms {
    // twilio api keys
    @Value("${twilio.acc.sid}")
    private String twilioSID;
    @Value("${twilio.auth.key}")
    private String twilioKey;
    @Value("${twilio.my.phone.number}")
    private String myPhoneNumber;

    @PostConstruct
    public void init() {
    }

    public void sendSmsMessage(String requestType, String contactNumber) {
        Twilio.init(twilioSID, twilioKey);
                Message.creator(
                        new PhoneNumber("+63" + contactNumber), // to
                        new PhoneNumber(myPhoneNumber), // from
                        "Kunin ang " + requestType + "sa Barangay Hall." // body (message)
                ).create();
    }
}
