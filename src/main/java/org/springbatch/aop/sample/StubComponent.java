package org.springbatch.aop.sample;

import org.springbatch.aop.sample.aop.OtherAnnotation;
import org.springframework.stereotype.Component;

@Component
public class StubComponent {

    @OtherAnnotation
    public void callMe(){ }
}
