curl -X POST -H "Authorization: Bearer ya29.GlvWBeAgapi-taaEUC5eQPyTXs2zJILDhMyup2MIK7L7JhKRToLzyyJxOTrPPLnA-PKXhmKqvhVrT_fANIkDcN6XijvEr0r7pagpcYvSWlR2C7ufOoAA-tFJJRP4" -H "Content-Type: application/json" -d '
{
"message":{
  "notification": {
    "title": "New Event is Created",
    "body": "replacevalue1",
  },
  "token": "replacevalue2"
  }
}' https://fcm.googleapis.com/v1/projects/hackathonesplit/messages:send