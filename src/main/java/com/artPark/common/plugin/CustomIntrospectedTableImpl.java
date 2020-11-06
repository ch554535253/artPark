package com.artPark.common.plugin;

import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

/**
 * @author  lbc
 *
 */
public class CustomIntrospectedTableImpl extends IntrospectedTableMyBatis3Impl{
	protected void calculateXmlAttributes(){
		setIbatis2SqlMapPackage(calculateSqlMapPackage());
	    setIbatis2SqlMapFileName(calculateIbatis2SqlMapFileName());
	    setMyBatis3XmlMapperFileName(calculateMyBatis3XmlMapperFileName());
	    setMyBatis3XmlMapperPackage(calculateSqlMapPackage());
	    
	    setIbatis2SqlMapNamespace(calculateIbatis2SqlMapNamespace());
	    setMyBatis3FallbackSqlMapNamespace(calculateMyBatis3FallbackSqlMapNamespace());
	    
	    setSqlMapFullyQualifiedRuntimeTableName(calculateSqlMapFullyQualifiedRuntimeTableName());
	    setSqlMapAliasedFullyQualifiedRuntimeTableName(calculateSqlMapAliasedFullyQualifiedRuntimeTableName());
	    
	    setCountByExampleStatementId("findCount");
	    setDeleteByExampleStatementId("deleteByExample");
	    setDeleteByPrimaryKeyStatementId("delete");
	    setInsertStatementId("insertAll");
	    setInsertSelectiveStatementId("insert");
	    setSelectAllStatementId("findAll");
	    setSelectByExampleStatementId("find");
	    setSelectByExampleWithBLOBsStatementId("selectByExampleWithBLOBs");
	    setSelectByPrimaryKeyStatementId("findOne");
	    setUpdateByExampleStatementId("updateByExample");
	    setUpdateByExampleSelectiveStatementId("updateByExampleSelective");
	    setUpdateByExampleWithBLOBsStatementId("updateByExampleWithBLOBs");
	    setUpdateByPrimaryKeyStatementId("updateByPrimaryKey");
	    setUpdateByPrimaryKeySelectiveStatementId("update");
	    setUpdateByPrimaryKeyWithBLOBsStatementId("updateByPrimaryKeyWithBLOBs");
	    setBaseResultMapId("basicResultMap");
	    setResultMapWithBLOBsId("resultMapWithBLOBs");
	    setExampleWhereClauseId("Example_Where_Clause");
	    setBaseColumnListId("baseColumnList");
	    setBlobColumnListId("blobColumnList");
	    setMyBatis3UpdateByExampleWhereClauseId("Update_By_Example_Where_Clause");
	}
	
	public String getAliasedFullyQualifiedTableNameAtRuntime() {
		String schemaTable = super.getAliasedFullyQualifiedTableNameAtRuntime();
		String[] tableNames = schemaTable.split("\\.");
		if (tableNames.length == 1) {
			return tableNames[0];
		} else if (tableNames.length == 2) {
		return "${LINANPAY_SCHEMA}." + tableNames[1];//对应mybatis-config.xml中所设置的别名
		} else {
			throw new RuntimeException("schemaTable Name invalid [" + schemaTable + "]");
		}
	}
	
	public String getFullyQualifiedTableNameAtRuntime() {
		String schemaTable = super.getFullyQualifiedTableNameAtRuntime();
		String[] tableNames = schemaTable.split("\\.");
		if (tableNames.length == 1) {
			return tableNames[0];
		} else if (tableNames.length == 2) {
			return "${LINANPAY_SCHEMA}." + tableNames[1];//对应mybatis-config.xml中所设置的别名
		} else {
			throw new RuntimeException("schemaTable Name invalid [" + schemaTable + "]");
		}
	}
	
}
