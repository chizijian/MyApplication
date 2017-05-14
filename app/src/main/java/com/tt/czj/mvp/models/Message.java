package com.tt.czj.mvp.models;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/3/25/0025.
 */
public class Message extends BmobObject {
    private String sendMessage_id;

    /**
     * Gets message goodsid.
     *
     * @return the message goodsid
     */
    public String getMessage_Goodsid() {
        return Message_Goodsid;
    }

    /**
     * Sets message goodsid.
     *
     * @param message_Goodsid the message goodsid
     */
    public void setMessage_Goodsid(String message_Goodsid) {
        Message_Goodsid = message_Goodsid;
    }

    private String Message_Goodsid;

    /**
     * Gets accept message id.
     *
     * @return the accept message id
     */
    public String getAcceptMessage_id() {
        return acceptMessage_id;
    }

    /**
     * Sets accept message id.
     *
     * @param acceptMessage_id the accept message id
     */
    public void setAcceptMessage_id(String acceptMessage_id) {
        this.acceptMessage_id = acceptMessage_id;
    }

    /**
     * Gets send message id.
     *
     * @return the send message id
     */
    public String getSendMessage_id() {
        return sendMessage_id;
    }

    /**
     * Sets send message id.
     *
     * @param sendMessage_id the send message id
     */
    public void setSendMessage_id(String sendMessage_id) {
        this.sendMessage_id = sendMessage_id;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    private String acceptMessage_id;
    private String message;

    /**
     * Instantiates a new Message.
     */
    public Message(){

    }

    /**
     * Instantiates a new Message.
     *
     * @param sender          the sender
     * @param accepter        the accepter
     * @param message         the message
     * @param Message_Goodsid the message goodsid
     */
    public Message(String sender,String accepter,String message,String Message_Goodsid){
        setAcceptMessage_id(accepter);
        setMessage(message);
        setMessage_Goodsid(Message_Goodsid);
        setSendMessage_id(sender);
    }
}
