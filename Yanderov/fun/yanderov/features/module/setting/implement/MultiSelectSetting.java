package fun.Yanderov.features.module.setting.implement;

import fun.Yanderov.features.module.setting.Setting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class MultiSelectSetting extends Setting {
   private List list;
   private List selected = new ArrayList();

   public MultiSelectSetting(String name, String description) {
      super(name, description);
   }

   public MultiSelectSetting value(String... settings) {
      this.list = Arrays.asList(settings);
      return this;
   }

   public MultiSelectSetting selected(String... settings) {
      this.selected = new ArrayList(Arrays.asList(settings));
      return this;
   }

   public MultiSelectSetting visible(Supplier visible) {
      this.setVisible(visible);
      return this;
   }

   public boolean isSelected(String name) {
      return this.selected.contains(name);
   }

   public List getList() {
      return this.list;
   }

   public List getSelected() {
      return this.selected;
   }

   public void setList(List list) {
      this.list = list;
   }

   public void setSelected(List selected) {
      this.selected = selected;
   }
}

