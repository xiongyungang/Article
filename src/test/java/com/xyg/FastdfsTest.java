package com.xyg;


import com.xyg.utils.FastDFSClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastdfsTest {
    @Test
    public void testFastDfsClient() throws Exception {
        FastDFSClient fastDFSClient = new FastDFSClient("E:\\fdfs.conf");
        String file = fastDFSClient.uploadFile("E:\\download.jpg");
        System.out.println(file);
    }
}
