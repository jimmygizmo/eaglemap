package ai.smartmetal.eaglemap.controller;

import ai.smartmetal.eaglemap.service.ProtoMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProtoMapController {

    @Autowired
    private ProtoMapService protoMapService;

    @GetMapping("/protomap")
    public Map<String, String> getProtomap() {
        String svgBase64 = protoMapService.generateRandomSvgBase64();
        return Map.of("svgimg", svgBase64);
    }
}
