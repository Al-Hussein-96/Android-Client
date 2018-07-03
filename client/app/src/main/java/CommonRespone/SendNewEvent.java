/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonRespone;

import java.io.Serializable;
import java.util.List;

import EventClass.Event_Class;

/**
 *
 * @author Moaz
 */
public class SendNewEvent extends Respone implements Serializable {

    public List<Event_Class> NewEvent;

    public SendNewEvent(List<Event_Class> NewEvent) {
        super(ResponeType.DONE);
        this.NewEvent = NewEvent;
    }
}
