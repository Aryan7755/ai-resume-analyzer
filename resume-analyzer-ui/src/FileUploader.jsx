import react, { useState } from 'react'
import axios from 'axios'
const FileUploader = () => {
    const [file, setFile] = useState(null);
    const [message, setMessage] = useState('');
    const handleUpload = async () => {
        if (file.size > 5 * 1024 * 1024) {
            setMessage("File is too large! Please select a file under 5MB.");
            return;
        }
        const formData = new FormData();
        formData.append('file', file);
        try {
            const response = await axios.post('http://localhost:8080/api/resumes/upload', formData);
            setMessage(response.data);
        } catch (error) {
            setMessage("Upload failed!");
        }
    };
    return (
        <div>
            <input type="file" onChange={e => setFile(e.target.files[0])} accept=".pdf,.docx" />
            <button onClick={handleUpload}>Upload</button>
            <p>{message}</p>
        </div>
    );
};
export default FileUploader;