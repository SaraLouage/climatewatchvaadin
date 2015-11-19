$wnd.MyWidgetset.runAsyncCallback3("function $getCellForEvent(this$static, event_0){\n  var column, row, td;\n  td = $getEventTargetCell(this$static, event_0.nativeEvent);\n  if (!td) {\n    return null;\n  }\n  row = $getParentElement_0(($clinit_DOMImpl() , td)).sectionRowIndex;\n  column = td.cellIndex;\n  return new HTMLTable$Cell(row, column);\n}\n\nfunction $getEventTargetCell(this$static, event_0){\n  var body_0, td, tr;\n  td = ($clinit_DOM() , ($clinit_DOMImpl() , impl_0).eventGetTarget(event_0));\n  for (; td; td = $getParentElement_0(td)) {\n    if ($equalsIgnoreCase($getPropertyString(td, 'tagName'), 'td')) {\n      tr = $getParentElement_0(td);\n      body_0 = $getParentElement_0(tr);\n      if (body_0 == this$static.bodyElem) {\n        return td;\n      }\n    }\n    if (td == this$static.bodyElem) {\n      return null;\n    }\n  }\n  return null;\n}\n\nfunction HTMLTable$Cell(rowIndex, cellIndex){\n  this.cellIndex_0 = cellIndex;\n  this.rowIndex = rowIndex;\n}\n\ndefineClass(814, 1, {}, HTMLTable$Cell);\n_.cellIndex_0 = 0;\n_.rowIndex = 0;\nfunction $select(this$static, p0, p1){\n  $invoke(this$static.val$handler, new Method(new Type(Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridServerRpc_2_classLit), 'select'), initValues(_3Ljava_lang_Object_2_classLit, $intern_5, 1, 3, [valueOf_45(p0), valueOf_45(p1)]));\n}\n\nfunction $loadNativeJs_0(store){\n  var data_0 = {setter:function(bean, value_0){\n    bean.columnCount = value_0.intValue();\n  }\n  , getter:function(bean){\n    return valueOf_45(bean.columnCount);\n  }\n  };\n  store.setPropertyData(Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'columnCount', data_0);\n  var data_0 = {setter:function(bean, value_0){\n    bean.changedY = value_0;\n  }\n  , getter:function(bean){\n    return bean.changedY;\n  }\n  };\n  store.setPropertyData(Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'changedY', data_0);\n  var data_0 = {setter:function(bean, value_0){\n    bean.changedColor = value_0;\n  }\n  , getter:function(bean){\n    return bean.changedColor;\n  }\n  };\n  store.setPropertyData(Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'changedColor', data_0);\n  var data_0 = {setter:function(bean, value_0){\n    bean.changedX = value_0;\n  }\n  , getter:function(bean){\n    return bean.changedX;\n  }\n  };\n  store.setPropertyData(Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'changedX', data_0);\n  var data_0 = {setter:function(bean, value_0){\n    bean.rowCount = value_0.intValue();\n  }\n  , getter:function(bean){\n    return valueOf_45(bean.rowCount);\n  }\n  };\n  store.setPropertyData(Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'rowCount', data_0);\n}\n\ndefineClass(633, 1, $intern_90);\n_.onSuccess_1 = function onSuccess_3(){\n  $setSuperClass(this.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, Lcom_vaadin_shared_AbstractComponentState_2_classLit);\n  $setClass(this.val$store, 'com.vaadin.ui.components.colorpicker.ColorPickerGrid', Lcom_vaadin_client_ui_colorpicker_ColorPickerGridConnector_2_classLit);\n  $setInvoker(this.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, '!new', new ConnectorBundleLoaderImpl$3$1$1);\n  $setInvoker(this.val$store, Lcom_vaadin_client_ui_colorpicker_ColorPickerGridConnector_2_classLit, '!new', new ConnectorBundleLoaderImpl$3$1$2);\n  $setReturnType(this.val$store, Lcom_vaadin_client_ui_colorpicker_ColorPickerGridConnector_2_classLit, 'getState', new Type(Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit));\n  $loadNativeJs_0(this.val$store);\n  $setPropertyType(this.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'columnCount', new Type(Ljava_lang_Integer_2_classLit));\n  $setPropertyType(this.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'changedY', new Type(_3Ljava_lang_String_2_classLit));\n  $setPropertyType(this.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'changedColor', new Type(_3Ljava_lang_String_2_classLit));\n  $setPropertyType(this.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'changedX', new Type(_3Ljava_lang_String_2_classLit));\n  $setPropertyType(this.val$store, Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit, 'rowCount', new Type(Ljava_lang_Integer_2_classLit));\n  $setLoaded_0((!impl_15 && (impl_15 = new ConnectorBundleLoaderImpl) , impl_15), this.this$1.packageName);\n}\n;\nfunction ConnectorBundleLoaderImpl$3$1$1(){\n}\n\ndefineClass(760, 1, $intern_113, ConnectorBundleLoaderImpl$3$1$1);\n_.invoke = function invoke_191(target, params){\n  return new ColorPickerGridState;\n}\n;\nfunction ConnectorBundleLoaderImpl$3$1$2(){\n}\n\ndefineClass(1353, 1, $intern_113, ConnectorBundleLoaderImpl$3$1$2);\n_.invoke = function invoke_192(target, params){\n  return new ColorPickerGridConnector;\n}\n;\nfunction ColorPickerGridConnector(){\n  AbstractComponentConnector.call(this);\n  this.rpc = dynamicCast(create_5(Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridServerRpc_2_classLit, this), 1067);\n}\n\ndefineClass(1439, 348, $intern_153, ColorPickerGridConnector);\n_.createWidget = function createWidget_4(){\n  return new VColorPickerGrid;\n}\n;\n_.getState = function getState_37(){\n  return !this.state && (this.state = $createState(this)) , dynamicCast(dynamicCast(this.state, 5), 131);\n}\n;\n_.getState_0 = function getState_38(){\n  return !this.state && (this.state = $createState(this)) , dynamicCast(dynamicCast(this.state, 5), 131);\n}\n;\n_.getWidget_0 = function getWidget_20(){\n  return dynamicCast($getWidget_1(this), 126);\n}\n;\n_.init = function init_16(){\n  $addDomHandler(dynamicCast($getWidget_1(this), 126), this, ($clinit_ClickEvent() , $clinit_ClickEvent() , TYPE_1));\n}\n;\n_.onClick = function onClick_50(event_0){\n  $select(this.rpc, dynamicCast($getWidget_1(this), 126).selectedX, dynamicCast($getWidget_1(this), 126).selectedY);\n}\n;\n_.onStateChanged = function onStateChanged_16(stateChangeEvent){\n  $onStateChanged_0(this, stateChangeEvent);\n  (stateChangeEvent.hasPropertyChanged('rowCount') || stateChangeEvent.hasPropertyChanged('columnCount') || stateChangeEvent.hasPropertyChanged('updateGrid')) && $updateGrid(dynamicCast($getWidget_1(this), 126), (!this.state && (this.state = $createState(this)) , dynamicCast(dynamicCast(this.state, 5), 131)).rowCount, (!this.state && (this.state = $createState(this)) , dynamicCast(dynamicCast(this.state, 5), 131)).columnCount);\n  if (stateChangeEvent.hasPropertyChanged('changedX') || stateChangeEvent.hasPropertyChanged('changedY') || stateChangeEvent.hasPropertyChanged('changedColor') || stateChangeEvent.hasPropertyChanged('updateColor')) {\n    $updateColor(dynamicCast($getWidget_1(this), 126), (!this.state && (this.state = $createState(this)) , dynamicCast(dynamicCast(this.state, 5), 131)).changedColor, (!this.state && (this.state = $createState(this)) , dynamicCast(dynamicCast(this.state, 5), 131)).changedX, (!this.state && (this.state = $createState(this)) , dynamicCast(dynamicCast(this.state, 5), 131)).changedY);\n    dynamicCast($getWidget_1(this), 126).gridLoaded || $invoke(this.rpc.val$handler, new Method(new Type(Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridServerRpc_2_classLit), 'refresh'), initValues(_3Ljava_lang_Object_2_classLit, $intern_5, 1, 3, []));\n  }\n}\n;\nfunction $createGrid(this$static){\n  this$static.grid = new Grid(this$static.rows_0, this$static.columns);\n  $setWidth_0(this$static.grid, '100%');\n  $setHeight_0(this$static.grid, '100%');\n  $addDomHandler(this$static.grid, this$static, ($clinit_ClickEvent() , $clinit_ClickEvent() , TYPE_1));\n  return this$static.grid;\n}\n\nfunction $updateColor(this$static, changedColor, changedX, changedY){\n  var c, element;\n  if (changedColor != null && changedX != null && changedY != null) {\n    if (changedColor.length == changedX.length && changedX.length == changedY.length) {\n      for (c = 0; c < changedColor.length; c++) {\n        element = $getElement_0(this$static.grid.cellFormatter, __parseAndValidateInt(changedX[c], 10, $intern_32, $intern_27), __parseAndValidateInt(changedY[c], 10, $intern_32, $intern_27));\n        $setPropertyImpl(element.style, 'background', changedColor[c]);\n      }\n    }\n    this$static.gridLoaded = true;\n  }\n}\n\nfunction $updateGrid(this$static, rowCount, columnCount){\n  this$static.rows_0 = rowCount;\n  this$static.columns = columnCount;\n  $remove_4(this$static, this$static.grid);\n  $add_3(this$static, $createGrid(this$static), 0, 0);\n}\n\nfunction VColorPickerGrid(){\n  AbsolutePanel.call(this);\n  this.rows_0 = 1;\n  this.columns = 1;\n  this.gridLoaded = false;\n  $add_3(this, $createGrid(this), 0, 0);\n}\n\ndefineClass(126, 624, {699:1, 383:1, 2030:1, 352:1, 2199:1, 1293:1, 2138:1, 108:1, 1948:1, 1979:1, 217:1, 286:1, 20:1, 126:1, 180:1}, VColorPickerGrid);\n_.addClickHandler = function addClickHandler_5(handler){\n  return $addDomHandler(this, handler, ($clinit_ClickEvent() , $clinit_ClickEvent() , TYPE_1));\n}\n;\n_.onClick = function onClick_51(event_0){\n  var cell;\n  cell = $getCellForEvent(this.grid, event_0);\n  if (!cell) {\n    return;\n  }\n  this.selectedY = cell.rowIndex;\n  this.selectedX = cell.cellIndex_0;\n}\n;\n_.columns = 0;\n_.gridLoaded = false;\n_.rows_0 = 0;\n_.selectedX = 0;\n_.selectedY = 0;\nfunction ColorPickerGridState(){\n  AbstractComponentState.call(this);\n}\n\ndefineClass(131, 5, {5:1, 291:1, 131:1, 3:1}, ColorPickerGridState);\n_.columnCount = 0;\n_.rowCount = 0;\nvar Lcom_vaadin_shared_ui_colorpicker_ColorPickerGridState_2_classLit = createForClass('com.vaadin.shared.ui.colorpicker.', 'ColorPickerGridState', 131), Lcom_vaadin_client_ui_colorpicker_ColorPickerGridConnector_2_classLit = createForClass('com.vaadin.client.ui.colorpicker.', 'ColorPickerGridConnector', 1439), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$3$1$1_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$3$1$1', 760), Lcom_vaadin_client_metadata_ConnectorBundleLoaderImpl$3$1$2_2_classLit = createForClass('com.vaadin.client.metadata.', 'ConnectorBundleLoaderImpl$3$1$2', 1353), Lcom_google_gwt_user_client_ui_HTMLTable$Cell_2_classLit = createForClass('com.google.gwt.user.client.ui.', 'HTMLTable$Cell', 814), Lcom_vaadin_client_ui_colorpicker_VColorPickerGrid_2_classLit = createForClass('com.vaadin.client.ui.colorpicker.', 'VColorPickerGrid', 126);\n$entry(onLoad)(3);\n\n//# sourceURL=MyWidgetset-3.js\n")
