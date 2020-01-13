package com.example.transactions.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@SpringBootTest
class FileTest {

    @Test
    public void constructorTestOk() {

        String header = "1b4d837dc253c43dba518963c71d0164f   001000009936613700000013939150000097972222";

        String transacciones = "27990e3e3813546eda46bf06b29f98c640000009698543     1\n" +
                "25c6d13964a8646228f084aaab0fe7ed90000009685761     1\n" +
                "2034520a0941540309598000c2bdc19610000006525212     1\n" +
                "2d75f0bdae673439d88b5cd5934e7188e0000008706802     1\n" +
                "280f5fefa2e0f43b9b04a85127cae2c9b0000009449894     1\n" +
                "28020a444e21e4c71abeb19808134450f0000009602433     2\n" +
                "29bd2c37857ba4ba98714a8ef03431ebd0000000876196     1\n" +
                "22c1bc6054dfb45ad90ee1d64cad235a40000009855813     2\n" +
                "206a00d9210784bfdada18701ca81edd90000004264144     2\n" +
                "29ce6fb0301e6423da0435531dac746aa0000004995963     1\n" +
                "242cf93cd35344339a80299ae1e8dd8e00000003122771     2\n" +
                "282eb8cdf4206488ab9bd35ba9ae9c97a0000002310524     2\n" +
                "2c4b583c3c1d84b7abc6ddf04cde86f760000009847260     1\n" +
                "27d0fa5d3e0344b3498310fcc119db03f0000000502732     1\n" +
                "2382ccedc5e3d438c854d049fe2d88ccc0000003003752     1\n" +
                "2c8d5d8fe35754d56a3ee6db466fe6add0000007191298     1\n" +
                "27569126a9a964deab547ae7436a78e720000002989178     2\n" +
                "2c587680324a348c3802940b32e2c7c510000000794631     2\n" +
                "27003692599ad429aba503e035f9762210000006127626     2\n" +
                "2cb40da7d2e3e499cb438d9b548039df00000005842787     2\n" +
                "247701ab61ac84f20a9885e89dbf8ef1c0000008495769     1\n" +
                "29e5615dc2ec8451ead904223531775f20000000721890     2\n" +
                "20f708292c2924f4cb35927a6dd2bb4db0000000818148     2\n" +
                "2d778a9bbbd1342919fda75e1fc4595fe0000007909504     2\n" +
                "2700dbedb590b4a0c8914b6c7eb67af020000007445389     2\n" +
                "2e2f39224c6514cb1825d1823bce1a86a0000000293432     1\n" +
                "2b49c977c8a5648cda360b07a9c11fb260000007626736     2\n" +
                "247e20720e1b34d40b927ff6ceb6c2a240000003127270     1\n" +
                "2130718ed4868494a91f97b098e932d310000005880513     1\n" +
                "27952a1ebf83d409fb313eead335c61830000004734811     1\n" +
                "23971a3f00c0140f4a8a83093577876280000008196065     2\n" +
                "25daba04bf83947fdb9a0b29a9b7360a60000007342039     1\n" +
                "23437b5a7f495472e9e82f163118c78d10000008664473     2\n" +
                "259df79a718c742c89d9eddf650e904ee0000001438153     2\n" +
                "23e2b38a9ac2449f486312a33510fa68b0000005641429     1\n" +
                "2828d6de8b88d4ba18939435b09d6c2000000007207210     2\n" +
                "28e337ee337174a93be01c88074ddbf090000003483964     2\n" +
                "20a68e585adcb4d7cb3323ac23897a2b60000005031167     1\n" +
                "262c1ec703b054f8c80de0b9be9a384800000000100138     2\n" +
                "2c02c1863681d4d1880580726ce11a9760000004562879     1\n" +
                "2130a8c0e34db4129846e5e9a612ea8db0000008581293     1\n" +
                "2e756ba25776f422eb9826f2b26b157be0000009688341     1\n" +
                "217d971a6b63242da94b733d5ecc238cb0000003080068     1\n" +
                "25072eb3b33a744cdbbf85c352462e8200000004526012     2\n" +
                "22475a91a073249d789d73bbe2ed198640000001778112     1\n" +
                "27213b6bffef546318d925b033687ce330000003256571     1\n" +
                "21517cb6c297e4a66b5e9821b93548d580000006830468     1\n" +
                "20f9ddf614777414787bf391e85715fc20000001271655     1\n";

        String descuentos = "3a9c5e125d3fa406997c93c1127c7d4400000000537642   0\n" +
                "313eb9e94d6564e5eb285f92533295ec30000000506343   1\n" +
                "3975cafcb68d6429dbbbf0ebea7c0c1210000000609242   2\n";

        String footer = "4               20200114d5ad08db86364243b7cfb28af5766f39\n";


        File file = new File(header, transacciones, descuentos, footer);

        assertThat(file.getHeader().getTipoRegistro(), is("1"));
        assertThat(file.getHeader().getIdPago(), is("b4d837dc253c43dba518963c71d0164f"));
        //assertThat(file.getHeader().getReservado(), is("   "));
        assertThat(file.getHeader().getMoneda(), is(Moneda.USD));
        assertThat(file.getHeader().getMontoTotal(), is(BigDecimal.valueOf(99366137)));
        assertThat(file.getHeader().getTotalDescuentos(), is(BigDecimal.valueOf(1393915)));

        assertThat(file.getTransaccions().size(), is(48));
        assertThat(file.getTransaccions().get(0).getTipoRegistro(), is("2"));
        assertThat(file.getTransaccions().get(0).getIdTransaccion(), is("7990e3e3813546eda46bf06b29f98c64"));
        assertThat(file.getTransaccions().get(0).getMonto(), is(new BigDecimal(9698543)));
        //assertThat(file.getTransaccions().get(0).getReservado(), is("     "));
        assertThat(file.getTransaccions().get(0).getTipo(), is(TipoTransaccion.APROBADO));

        assertThat(file.getDescuentos().size(), is(3));
        assertThat(file.getDescuentos().get(0).getTipoRegistro(), is("3"));
        assertThat(file.getDescuentos().get(0).getIdDescuento(), is("a9c5e125d3fa406997c93c1127c7d440"));
        assertThat(file.getDescuentos().get(0).getMonto(), is(new BigDecimal(537642)));
        //assertThat(file.getDescuentos().get(0).getReservado(), is("   "));
        assertThat(file.getDescuentos().get(0).getTipo(), is(TipoDescuento.IVA));

        assertThat(file.getFooter(), is(notNullValue()));
        assertThat(file.getFooter().getTipoRegistro(), is("4"));
        //assertThat(file.getFooter().getReservado(), is("               "));
        assertThat(file.getFooter().getFechaPago().toString(), is("2020-01-14"));
        assertThat(file.getFooter().getIdCliente(), is("d5ad08db86364243b7cfb28af5766f39"));

    }

}