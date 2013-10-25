@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.6' )
import groovyx.net.http.RESTClient

import javax.imageio.ImageIO
import javax.imageio.ImageReadParam
import javax.imageio.ImageReader
import javax.imageio.stream.ImageInputStream
import java.awt.*
@Grab(group = 'org.codehaus.groovy.modules.http-builder', module = 'http-builder', version = '0.6') @Grab(group = 'org.codehaus.groovy.modules.http-builder', module = 'http-builder', version = '0.6') @Grab(group = 'org.codehaus.groovy.modules.http-builder', module = 'http-builder', version = '0.6') @Grab(group = 'org.codehaus.groovy.modules.http-builder', module = 'http-builder', version = '0.6') @Grab(group = 'org.codehaus.groovy.modules.http-builder', module = 'http-builder', version = '0.6') @Grab(group = 'org.codehaus.groovy.modules.http-builder', module = 'http-builder', version = '0.6') @Grab(group = 'org.codehaus.groovy.modules.http-builder', module = 'http-builder', version = '0.6')
import java.awt.image.BufferedImage

import static groovyx.net.http.ContentType.URLENC

/**
 * Created with IntelliJ IDEA.
 * User: danielhonig
 * Date: 9/8/13
 * Time: 12:26 PM
 * To change this template use File | Settings | File Templates.
 */

def site = new groovyx.net.http.HTTPBuilder( 'http://mule.itemize.com:9090' )
site.auth.basic 'itemize', 'itemize'
secrets = site.get( path:'/api/v1/documents/unique1001' )
decodedBytes= secrets.document.content.decodeBase64()
new File('./some.jpg').withOutputStream{ it.write decodedBytes}

