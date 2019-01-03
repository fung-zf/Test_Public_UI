package com.xzkydz.function.gaspublicfunction;

import com.xzkydz.function.pdf.PdfInfBean;
import com.xzkydz.function.pdf.PdfListActivity;
import java.util.ArrayList;
import java.util.List;

public class MyPdfListActivity extends PdfListActivity {


    @Override
    public List<PdfInfBean> getPdfInf() {

        List<PdfInfBean> pdfInfBeanList = new ArrayList<>();
        //这里只是示例，"煤矿安全规程"是内置的，不需要设置路径，如果没有别的PDF文件可以直接返回null值。
        PdfInfBean pf = new PdfInfBean("煤矿安全规程","standard/meikuanganquan.pdf");
        pdfInfBeanList.add(pf);

        return null;
    }
}
