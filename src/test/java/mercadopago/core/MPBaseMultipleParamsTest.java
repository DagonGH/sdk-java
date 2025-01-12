package mercadopago.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import com.mercadopago.MercadoPago;
import com.mercadopago.core.MPBase;
import com.mercadopago.core.MPResourceArray;
import com.mercadopago.core.annotations.rest.DELETE;
import com.mercadopago.core.annotations.rest.GET;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import java.util.HashMap;
import java.util.TimeZone;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Mercado Pago SDK
 * MPBase Test for methods with params Class
 */
public class MPBaseMultipleParamsTest extends MPBase {

    @BeforeClass
    public static void beforeTest() throws MPException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        MercadoPago.SDK.cleanConfiguration();
        MercadoPago.SDK.setConfiguration("credentials.properties");
    }

    @GET(path="/loadpath/slug/:param1")
    public static MPBaseMultipleParamsTest findById(String id) throws MPException {
        return processMethod(MPBaseMultipleParamsTest.class, "findById", id, WITHOUT_CACHE);
    }

    @GET(path="/getpath/slug/:param1/otherslug/:param2")
    public static MPResourceArray all(String param1, String param2) throws MPException {
        return processMethodBulk(MPBaseMultipleParamsTest.class, "all", param1, param2, WITHOUT_CACHE);
    }

    @DELETE(path="/delete/slug/:card_id/otherslug/:param2/:param3")
    public MPBaseMultipleParamsTest delete(String card_id, String param2, String param3) throws MPException {
        HashMap<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("card_id", card_id);
        mapParams.put("param2", param2);
        mapParams.put("param3", param3);
        return super.processMethod(MPBaseMultipleParamsTest.class, this, "delete", mapParams, WITHOUT_CACHE);
    }

    /**
     * Test MPBase using a method with a single param
     */
    @Test
    public void singleParamsMethdTest() throws Exception {
        MPBaseMultipleParamsTest resource = MPBaseMultipleParamsTest.findById("some_id");
        assertEquals("GET", resource.getLastApiResponse().getMethod());
        assertEquals("https://api.mercadopago.com/loadpath/slug/some_id", resource.getLastApiResponse().getUrl());

    }

    /**
     * Test MPBase using a method with two params
     */
    @Ignore
    @Test
    public void twoParamsMethdTest() throws Exception {
        MPResourceArray resourceArray = MPBaseMultipleParamsTest.all("test1", "test2");
        assertEquals("GET", resourceArray.getLastApiResponse().getMethod());
        assertEquals("https://api.mercadopago.com/getpath/slug/test1/otherslug/test2?access_token=" + MercadoPago.SDK.getAccessToken(), resourceArray.getLastApiResponse().getUrl());
    }

    /**
     * Test MPBase using a method with three params
     */
    @Test
    public void threeParamsMethdTest() throws Exception {
        MPBaseMultipleParamsTest resource = new MPBaseMultipleParamsTest();
        resource.delete("test1", "test2", "test3");
        assertEquals("DELETE", resource.getLastApiResponse().getMethod());
        assertEquals("https://api.mercadopago.com/delete/slug/test1/otherslug/test2/test3", resource.getLastApiResponse().getUrl());
    }

    @Test
    public void idempotenceKeyTest() throws MPException {
        Preference preference = new Preference();

        assertNull(preference.getIdempotenceKey());

        Exception exception = null;
        try {
            preference.setIdempotenceKey("someKey");
        } catch (MPException mpException) {
            assertEquals("Preference does not admit an idempotence key", mpException.getMessage());
            exception = mpException;
        }
        assertSame(MPException.class, exception.getClass());
    }

}
