## WADL Examples

The Web Application Description Language (WADL) is a machine-readable XML description of HTTP-based web services. WADL models the resources provided by a service and the relationships between them. WADL is intended to simplify the reuse of web services that are based on the existing HTTP architecture of the Web.

WADL was submitted to the World Wide Web Consortium by Sun Microsystems on 31 August 2009, but the consortium has no current plans to standardize it. WADL is the REST equivalent of SOAP's Web Services Description Language (WSDL), which can also be used to describe REST web services.

## API description

In the ```resources``` folder, you will find a fake API and models description using WADL and XSD.

## Java code

The Java part of this project shows a trick for marshalling/unmarshalling objects and list using Jackson 2 and WADL.
