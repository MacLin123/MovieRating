package com.movierating.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

    private Logger log = LoggerFactory.getLogger(FileUploadExceptionAdvice.class);

//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    public void handleMaxSizeException(
//            MaxUploadSizeExceededException exc,
//            HttpServletRequest request,
//            HttpServletResponse response) {
//
//        log.error("Exception!!!! MAX FIX SIZE");
//
////        ModelAndView modelAndView = new ModelAndView("file");
////        modelAndView.getModel().put("message", "File too large!");
////        return modelAndView;
//        try {
//            response.sendError(400,"max file siz is 1");
//        } catch (IOException e) {
//           log.warn(e.getMessage());
//        }
//    }

    @ResponseBody
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxUploadSizeExceededException(final MaxUploadSizeExceededException ex, WebRequest request) {
        String message = "Attempt to upload file with the size exceeded max allowed value = "
                + Config.MAX_FILE_SIZE.getValue() + " bytes.";
        log.warn(message);
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(message);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity handleFileUploadingError(MultipartException exception) {
        log.warn("Failed to upload attachment", exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

}