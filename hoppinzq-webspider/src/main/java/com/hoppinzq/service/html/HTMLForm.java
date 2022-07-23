package com.hoppinzq.service.html;


import java.net.URLEncoder;

/**
 * @author: zq
 */
public class HTMLForm extends AttributeList {
    protected String _method;
    protected String _action;

    public HTMLForm(String method, String action) {
        this._method = method;
        this._action = action;
    }

    public String getAction() {
        return this._action;
    }

    public String getMethod() {
        return this._method;
    }

    public void addInput(String name, String value, String type, String prompt, AttributeList attributeList) {
        HTMLForm.FormElement formElement = new HTMLForm.FormElement();
        formElement.setName(name);
        formElement.setValue(value);
        formElement.setType(type.toUpperCase());
        formElement.setOptions(attributeList);
        formElement.setPrompt(prompt);
        this.add(formElement);
    }

    public String toString() {
        String s = "";

        for(int i = 0; this.get(i) != null; ++i) {
            Attribute attribute = this.get(i);
            if (s.length() > 0) {
                s = s + "&";
            }

            s = s + attribute.getName();
            s = s + "=";
            if (attribute.getValue() != null) {
                s = s + URLEncoder.encode(attribute.getValue());
            }
        }

        return s;
    }

    public class FormElement extends Attribute {
        protected String _type;
        protected AttributeList _options;
        protected String _prompt;

        public FormElement() {
        }

        public void setOptions(AttributeList attributeList) {
            this._options = attributeList;
        }

        public AttributeList getOptions() {
            return this._options;
        }

        public void setType(String type) {
            this._type = type;
        }

        public String getType() {
            return this._type;
        }

        public String getPrompt() {
            return this._prompt;
        }

        public void setPrompt(String prompt) {
            this._prompt = prompt;
        }
    }
}
