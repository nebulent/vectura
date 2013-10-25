import groovy.json.*

def json = new JsonBuilder()

json.ItemizeMessage {
    'OriginQueue' 'DataExtractionQueue'
    'OriginModule' 'RE'
    'DocumentId' 'http://mule.itemize.com:9090/api/v1/accounts/mfedorov/documents/520cc78fe4b0a8668801a030'
    'ProcessedAt' '2013-06-20T08:09:16.332-04:00'
    'Result' 'true'
    'EmailDetails' {
        'ToUserId' '4784'
        'EmailStageId' '614553'
        'FromAddress' 'mailer@fins.com'

    }
    'DocumentURI' 'http://mule.itemize.com:9090/api/v1/accounts/mfedorov/documents/520cc78fe4b0a8668801a030'
}


// We can even pretty print the JSON output
def prettyJson = JsonOutput.prettyPrint(json.toString())


print prettyJson;
assert prettyJson == '''{
    "ItemizeMessage": {
        "Origin": {
            "from": "mrhaki",
            "to": [
                "Groovy Users",
                "Java Users"
            ]
        },
        "body": "Check out Groovy's gr8 JSON support."
    }
}'''
