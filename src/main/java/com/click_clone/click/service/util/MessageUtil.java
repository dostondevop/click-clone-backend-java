package com.click_clone.click.service.util;

public interface MessageUtil {
    String OTP_MESSAGE_TITLE = "Verification Code";
    String OTP_MESSAGE = """
            Attention! The code grants the right to spend your money!
            DO NOT SHARE the code with ANYONE (CLICK employees will NEVER ask for it).
            Beware of fraudsters!
            Code:\s""";

    String USER_NOT_FOUND_ERROR = "User not found ERROR.";
    String SERVICE_NOT_FOUND_ERROR = "Service not found ERROR.";
    String INPUT_NOT_FOUND_ERROR = "Input not found ERROR.";
    String CATEGORY_NOT_FOUND_ERROR = "Category not found ERROR.";
    String POSTER_NOT_FOUND_ERROR = "Poster not found ERROR.";
    String JWT_WAS_EXPIRED_OR_INCORRECT_ERROR = "JWT was expired or incorrect ERROR.";
    String EXPIRED_JWT_TOKEN_ERROR = "Expired JWT token ERROR.";
    String UNSUPPORTED_JWT_TOKEN_ERROR = "Unsupported JWT token ERROR.";
    String JWT_TOKEN_COMPACT_OF_HANDLER_IS_INVALID_ERROR = "JWT token compact of handler is invalid ERROR.";
    String DEVICE_NOT_FOUND_ERROR = "Device not found ERROR.";
    String CARD_NOT_FOUND_ERROR = "Card not found ERROR.";
    String ATTACHMENT_NOT_FOUND_ERROR = "Attachment not found ERROR.";
    String SELECT_ITEM_NOT_FOUND_ERROR = "Select Item not found ERROR.";
    String HOME_NOT_FOUND_ERROR = "Home not found ERROR.";
    String INVALID_JSON_DATA_INPUT_ERROR = "Invalid JSON data input value ERROR ";
    String FAVORITE_NOT_FOUND_ERROR = "Featured not found ERROR";
}