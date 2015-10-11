/**
 *
 */
package com.juneix.frame.context;

import com.junesix.common.context.ClientVersion;
import com.junesix.common.context.ClientVersion.Version;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jolestar
 */
public class ClientVersionTest {

    @Test
    public void testClientVersion() {
        String header = "2.4.1.0-1.0.0.0-udid-iPhone-weimi";
        ClientVersion version = ClientVersion.valueOf(header);
        Assert.assertEquals(2, version.sdkVersion.major);
        Assert.assertEquals(4, version.sdkVersion.minor);
        Assert.assertEquals(1, version.sdkVersion.revision);

        Assert.assertEquals(1, version.clientVersion.major);
        Assert.assertEquals(0, version.clientVersion.minor);
        Assert.assertEquals(0, version.clientVersion.revision);

        Assert.assertEquals("udid", version.udid);
        Assert.assertEquals("iPhone", version.device);
        Assert.assertEquals("weimi", version.channel);

        ClientVersion version2 = ClientVersion.valueOf(version.toString());
        Assert.assertEquals(version, version2);
        System.out.println(version.toString());
        Assert.assertEquals(header, version2.toString());
    }


    @Test
    public void testClientVersionChannel() {
        String header = "0-0-unknow-unknow-linggengxin";
        ClientVersion version = ClientVersion.valueOf(header);
        System.out.println(version.toString());
    }

    @Test
    public void testClientVersionError() {
        ClientVersion version = ClientVersion.valueOf("aaaa");
        Assert.assertNotNull(version);
        Assert.assertEquals(ClientVersion.NULL, version);

        version = ClientVersion.valueOf("aaaa-");
        Assert.assertNotNull(version);
        Assert.assertEquals(ClientVersion.NULL, version);

        version = ClientVersion.valueOf("aaaa-bbb");
        Assert.assertNotNull(version);
        Assert.assertEquals(ClientVersion.NULL, version);

        version = ClientVersion.valueOf(null);
        Assert.assertNotNull(version);
        Assert.assertEquals(ClientVersion.NULL, version);

        version = ClientVersion.valueOf("");
        Assert.assertNotNull(version);
        Assert.assertEquals(ClientVersion.NULL, version);
    }

    @Test
    public void testCompare() {
        Assert.assertEquals(1, Version.valueOf("1.1.75").compareTo(null));
        Assert.assertEquals(1, Version.valueOf("1.1.75").compareTo(Version.valueOf("0.1.75")));
        Assert.assertEquals(1, Version.valueOf("1.1.75").compareTo(Version.valueOf("1.0.75")));
        Assert.assertEquals(1, Version.valueOf("1.1.75").compareTo(Version.valueOf("1.1.74")));

        Assert.assertEquals(0, Version.valueOf("1.1.75").compareTo(Version.valueOf("1.1.75")));

        Assert.assertEquals(-1, Version.valueOf("1.1.75").compareTo(Version.valueOf("2.1.75")));
        Assert.assertEquals(-1, Version.valueOf("1.1.75").compareTo(Version.valueOf("1.2.75")));
        Assert.assertEquals(-1, Version.valueOf("1.1.75").compareTo(Version.valueOf("1.1.76")));
    }

}
