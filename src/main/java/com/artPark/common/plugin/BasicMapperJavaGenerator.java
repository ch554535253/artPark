package com.artPark.common.plugin;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractJavaGenerator;

/**
 * @author lbc
 *
 */
public class BasicMapperJavaGenerator extends AbstractJavaGenerator{

	@Override
	public List<CompilationUnit> getCompilationUnits() {
		String pack = this.context.getProperty("basicMapperTargetPackage");
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(pack + "." + this.introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "Mapper");
//		int size = this.introspectedTable.getPrimaryKeyColumns().size();
//		String primaryKeyType = "com.bocom.bbip.data.domain.NoId";
//		if (size != 0) {
//			primaryKeyType = size > 1 ? this.introspectedTable.getPrimaryKeyType() : ((IntrospectedColumn)this.introspectedTable.getPrimaryKeyColumns().get(0)).getFullyQualifiedJavaType().toString();
//		}
		Interface interfaze = new Interface(type);
		interfaze.setVisibility(JavaVisibility.PUBLIC);
		interfaze.addImportedType(new FullyQualifiedJavaType("com.zhifu.gateway.persisten.common.BasicMapper"));
		interfaze.addImportedType(new FullyQualifiedJavaType(this.introspectedTable.getBaseRecordType()));
//		if (size == 0) {
//			interfaze.addImportedType(new FullyQualifiedJavaType("com.bocom.bbip.data.domain.NoId"));
//		} else if (size > 1) {
//			interfaze.addImportedType(new FullyQualifiedJavaType(this.introspectedTable.getPrimaryKeyType()));
//		}
		interfaze.addSuperInterface(new FullyQualifiedJavaType("com.zhifu.gateway.persisten.common.BasicMapper<" + this.introspectedTable.getBaseRecordType() + ">"));
		List<CompilationUnit> answer = new ArrayList();
		answer.add(interfaze);
		return answer;
	}
	
	
}
