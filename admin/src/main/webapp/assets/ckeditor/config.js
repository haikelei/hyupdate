/**
 * @license Copyright (c) 2003-2018, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
    config.language = 'zh-cn'; /*将编辑器的语言设置为中文*/
	config.uiColor = '#a2b1af'; /*设置编辑器的背景颜色*/
    config.image_previewText = ' ';/*去掉图片预览框的文字*/
    config.filebrowserUploadUrl="/hy/file/uploadfileCKeditor"; /*开启工具栏“图像”中文件上传功能，后面的url为图片上传要指向的的action或servlet*/
};
