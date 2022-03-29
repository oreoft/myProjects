package cn.someget.mixtools.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvReadConfig;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 读取文件相关
 *
 * @author zyf
 * @date 2022-03-29 11:03
 */
public class ReaderUtils {

    /**
     * 读入CSV文件
     */
    public static List<CsvRow> readCsvFile(String readCsvFilePath) {
        CsvReadConfig csvReadConfig = new CsvReadConfig();
        // 设置分隔符, 里面还有方法可以设置是否跳空, 是否包含header
        csvReadConfig.setFieldSeparator('|');
        // true是不包含
        csvReadConfig.setContainsHeader(true);
        CsvReader csvReader = new CsvReader(FileUtil.file(readCsvFilePath), Charset.defaultCharset(), csvReadConfig);
        return csvReader.read().getRows();
    }
}
