def userId = "mfedorov";

def feed = new com.itemize.services.model.core_2013_07_24.EnvelopeType();
feed.setCustomerId("CUSTOMER_ITEMIZE");
feed.setId(java.util.UUID.randomUUID().toString());
feed.setUserId(userId);
feed.setTo(feed.getUserId() + "@itemize.com");
feed.setFrom("orders@amazon.com");
feed.setSubject("Thank you for the order #" + org.apache.commons.lang.RandomStringUtils.randomAlphanumeric(10));
feed.setStatusCode("P");
feed.setReceivedDate(new java.util.Date());
feed.setSentDate(new java.util.Date());


def name = org.apache.commons.lang.RandomStringUtils.randomAlphabetic(5);
def doc = new com.itemize.services.model.core_2013_07_24.DocumentType();

doc.setCustomerId("CUSTOMER_ITEMIZE");
doc.setId(java.util.UUID.randomUUID().toString());
doc.setUserId(userId);
doc.setFormat("TXT");
doc.setName("Widget " + name);
doc.setTitle("Title for " + name);
doc.setTags("tags, tags, " + name);
doc.setDateCreated(new java.util.Date());

feed.docs.add(doc);

name = org.apache.commons.lang.RandomStringUtils.randomAlphabetic(5);
doc = new com.itemize.services.model.core_2013_07_24.DocumentType();

doc.setCustomerId("CUSTOMER_ITEMIZE");
doc.setId(java.util.UUID.randomUUID().toString());
doc.setUserId(userId);
doc.setFormat("TXT");
doc.setName("Widget " + name);
doc.setTitle("Title for " + name);
doc.setTags("tags, tags, " + name);
doc.setDateCreated(new java.util.Date());

feed.docs.add(doc);

return feed;