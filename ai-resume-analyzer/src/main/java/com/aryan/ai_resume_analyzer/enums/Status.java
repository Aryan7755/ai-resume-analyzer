package com.aryan.ai_resume_analyzer.enums;

public enum Status {
    UPLOADED,   //Recieved by Backend and Stored locally
    PROCESSING, //currently being parsed or sent to AI
    COMPLETED,  //AI analysis finished and results are saved
    FAILED, ERROR, EXTRACTED,     //Error in analysis
    ERROR_ENCRYPTED,  // New: For password-protected files
    ERROR_IMAGE_ONLY  // New: For scanned PDFs that need OCR
}
