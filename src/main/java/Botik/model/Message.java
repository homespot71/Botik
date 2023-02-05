package Botik.model;

import javax.persistence.*;


@Entity
@Table(name = "message")
public class Message {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "group_title")
    private String groupTitle;

    @Column(name = "group_id")
    private long groupId;

    @Column(name = "text")
    private String text;

    @Column(name = "user_name")
    private String userName;

    private int messageId;

    private boolean isNew = true;

    public Message() {

    }

    public Message(String groupTitle, long groupId, String text, String userName) {
        this.groupTitle = groupTitle;
        this.groupId = groupId;
        this.text = text;
        this.userName = userName;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public long getGroupId() {
        return groupId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", groupTitle='" + groupTitle + '\'' +
                ", groupId=" + groupId +
                ", text='" + text + '\'' +
                ", userName='" + userName + '\'' +
                ", isNew=" + isNew +
                '}';
    }
}
