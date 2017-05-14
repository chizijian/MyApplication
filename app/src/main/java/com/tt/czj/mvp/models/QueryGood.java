package com.tt.czj.mvp.models;

import java.util.List;

/**
 * Created by czj on 2016/5/16 0016.
 */
public class QueryGood {
    /**
     * The constant TAG.
     */
    public static final String TAG = "QueryGoods";
    private String title;
    private String description;
    private String new_degree;
    private String price;
    private String location;
    private String mount;

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets images.
     *
     * @return the images
     */
    public List<String> getImages() {
        return images;
    }

    /**
     * Sets images.
     *
     * @param images the images
     */
    public void setImages(List<String> images) {
        this.images = images;
    }

    /**
     * Gets mount.
     *
     * @return the mount
     */
    public String getMount() {
        return mount;
    }

    /**
     * Sets mount.
     *
     * @param mount the mount
     */
    public void setMount(String mount) {
        this.mount = mount;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Gets new degree.
     *
     * @return the new degree
     */
    public String getNew_degree() {
        return new_degree;
    }

    /**
     * Sets new degree.
     *
     * @param new_degree the new degree
     */
    public void setNew_degree(String new_degree) {
        this.new_degree = new_degree;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    private User user;
    private List<String> images;
}
