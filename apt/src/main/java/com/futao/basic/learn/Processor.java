//package com.futao.basic.learn;
//
//import com.squareup.javapoet.CodeBlock;
//import com.squareup.javapoet.JavaFile;
//import com.squareup.javapoet.MethodSpec;
//
//import javax.annotation.processing.*;
//import javax.lang.model.SourceVersion;
//import javax.lang.model.element.Modifier;
//import javax.lang.model.element.TypeElement;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @author futao
// * @date 2020/4/16.
// */
//@SupportedSourceVersion(SourceVersion.RELEASE_8)
//public class Processor extends AbstractProcessor {
//
//    /**
//     * 文件相关的辅助类 用于生成新的源文件、class等
//     */
//    private Filer filer;
//
//    @Override
//    public synchronized void init(ProcessingEnvironment processingEnv) {
//        super.init(processingEnv);
//        filer = processingEnv.getFiler();
//    }
//
//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        // 支持处理的注解类型
//        Set<String> annos = new HashSet<>();
//        annos.add(JsonString.class.getCanonicalName());
//        return annos;
//    }
//
//
//    @Override
//    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//        //具体处理注解的逻辑，控制代码的生成
//        MethodSpec.Builder builder = MethodSpec.methodBuilder("printJsonString")
//                .addModifiers(Modifier.PUBLIC)
//                .returns(String.class.getComponentType())
//                .addStatement(CodeBlock.builder().build());
//
//        JavaFile.builder("com.futao.").build().writeTo(filer);
//        return false;
//    }
//}
