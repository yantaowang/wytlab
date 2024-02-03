package com.wyt.wytlab.ai;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.*;

import java.util.List;
import java.util.regex.Pattern;

public class TikTokenDemo {
    public static void main(String[] args) {
        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
        Encoding enc = registry.getEncoding(EncodingType.CL100K_BASE);
        List<Integer> encoded = enc.encode("This is a sample sentence.");
        // encoded = [2028, 374, 264, 6205, 11914, 13]
        String decoded = enc.decode(encoded);
        System.out.println(encoded);
        System.out.println(decoded);
        Encoding secondEnc = registry.getEncodingForModel(ModelType.TEXT_EMBEDDING_ADA_002);
    }
}
