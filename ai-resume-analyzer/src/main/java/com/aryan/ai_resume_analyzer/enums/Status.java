package com.aryan.ai_resume_analyzer.enums;

public enum Status {
    UPLOADED,   //Recieved by Backend and Stored locally
    PROCESSING, //currently being parsed or sent to AI
    COMPLETED,  //AI analysis finished and results are saved
    FAILED,     //Error in analysis
}
