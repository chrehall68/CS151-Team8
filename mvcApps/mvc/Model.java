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

    public <T> T as(Class<T> cls) throws ClassCastException {
        if (cls.isInstance(this)) {
            return cls.cast(this);
        }
        throw new ClassCastException("Model must instantiate " + cls.getName());
    }

    public void changed() {
        unsavedChanges = true;
        notifySubscribers();
    }


}