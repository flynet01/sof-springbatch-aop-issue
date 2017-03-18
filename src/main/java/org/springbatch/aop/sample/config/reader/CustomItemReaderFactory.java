package org.springbatch.aop.sample.config.reader;

import org.springbatch.aop.sample.aop.SaveAndErrors;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
public class CustomItemReaderFactory {

    @SaveAndErrors
    public ItemReader<String> createNewReader() throws Exception {
        throw new Exception("errorTrapped");
    }
}
