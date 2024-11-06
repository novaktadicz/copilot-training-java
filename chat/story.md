# Hide Confidential Messages

## Description
Some messages contain confidential information. If the message is recognized as confidential, the system should hide that message (mask entire message with stars). Message is considered confidential if the message text contains word "CONFIDENTIAL" (no matter the casing) and the text length is not longer than 20 characters.

If the message content contains word "CONFIDENTIAL" and is longer than 20 characters, then this message should be flagged as fraudulent.

This logic should be applied to fetching chat history method, and not to chat (generate response) functionality.

## Acceptance Criteria
AC1: Content of the confidential messages is masked.

AC2: Content of the messages that are not confidential is not masked.

AC3: Fraudulent messages are properly tagged.