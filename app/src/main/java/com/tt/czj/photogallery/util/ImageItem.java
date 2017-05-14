package com.tt.czj.photogallery.util;

import android.graphics.Bitmap;

import java.io.IOException;
import java.io.Serializable;


/**
 * The type Image item.
 */
public class ImageItem implements Serializable {
    /**
     * The Image id.
     */
    public String imageId;
    /**
     * The Thumbnail path.
     */
    public String thumbnailPath;
    /**
     * The Image path.
     */
    public String imagePath;
	private Bitmap bitmap;
	private String imageName ;

    /**
     * Gets image name.
     *
     * @return the image name
     */
    public String getImageName() {
		return imageName;
	}

    /**
     * Sets image name.
     *
     * @param imageName the image name
     */
    public void setImageName(String imageName) {
		this.imageName = imageName;
	}

    /**
     * The Is selected.
     */
    public boolean isSelected = false;

    /**
     * Gets image id.
     *
     * @return the image id
     */
    public String getImageId() {
		return imageId;
	}

    /**
     * Sets image id.
     *
     * @param imageId the image id
     */
    public void setImageId(String imageId) {
		this.imageId = imageId;
	}

    /**
     * Gets thumbnail path.
     *
     * @return the thumbnail path
     */
    public String getThumbnailPath() {
		return thumbnailPath;
	}

    /**
     * Sets thumbnail path.
     *
     * @param thumbnailPath the thumbnail path
     */
    public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

    /**
     * Gets image path.
     *
     * @return the image path
     */
    public String getImagePath() {
		return imagePath;
	}

    /**
     * Sets image path.
     *
     * @param imagePath the image path
     */
    public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

    /**
     * Is selected boolean.
     *
     * @return the boolean
     */
    public boolean isSelected() {
		return isSelected;
	}

    /**
     * Sets selected.
     *
     * @param isSelected the is selected
     */
    public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

    /**
     * Gets bitmap.
     *
     * @return the bitmap
     */
    public Bitmap getBitmap() {
		if(bitmap == null){
			try {
				bitmap = Bimp.revitionImageSize(imagePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bitmap;
	}

    /**
     * Sets bitmap.
     *
     * @param bitmap the bitmap
     */
    public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	
	
}
