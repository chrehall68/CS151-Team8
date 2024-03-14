package mvc;

import java.io.Serializable;

public abstract class Model extends Publisher implements Serializable {
    private boolean unsavedChanges = false;
    private String fileName = null;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setUnsavedChanges(boolean unsavedChanges) {
        this.unsavedChanges = unsavedChanges;
    }

    public boolean hasUnsavedChanges() {
        return unsavedChanges;
    }

    /**
     * Recommended way to cast a model
     * @param cls - the class to cast to
     * @return T - the casted model
     * @param <T> - the type to cast to
     * @throws ClassCastException - if model is not of the provided class
     */
    public <T> T as(Class<T> cls) throws ClassCastException {
        if (cls.isInstance(this)) {
            return cls.cast(this);
        }
        throw new ClassCastException("Model must instantiate " + cls.getName());
    }

    /**
     * Customizations should prefer to use `changed` instead of `notifySubscribers`
     * since `changed` also sets the unsavedChanges flag.
     */
    public void changed() {
        unsavedChanges = true;
        notifySubscribers();
    }


}