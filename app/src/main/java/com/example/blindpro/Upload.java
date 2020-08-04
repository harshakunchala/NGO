package com.example.blindpro;


public class Upload {

    public String name;
    public String url;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Upload() {
    }

    public Upload(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}

// textViewStatus.setText("File Uploaded Successfully");
//         Log.d(TAG,""+taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
//         Upload upload = new Upload(editTextFilename.getText().toString(), taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
//         mDatabaseReference.child(mDatabaseReference.push().getKey()).setValue(upload);