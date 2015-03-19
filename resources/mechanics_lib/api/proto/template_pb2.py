# Generated by the protocol buffer compiler.  DO NOT EDIT!

from google.protobuf import descriptor
from google.protobuf import message
from google.protobuf import reflection
from google.protobuf import descriptor_pb2
# @@protoc_insertion_point(imports)


import symbolic_pb2

DESCRIPTOR = descriptor.FileDescriptor(
  name='template.proto',
  package='FabByExample.proto',
  serialized_pb='\n\x0etemplate.proto\x12\x12\x46\x61\x62\x42yExample.proto\x1a\x0esymbolic.proto\"W\n\x0bTemplateSet\x12.\n\x08template\x18\x01 \x03(\x0b\x32\x1c.FabByExample.proto.Template\x12\x18\n\x10root_template_id\x18\x02 \x01(\x05\"\xf0\x01\n\x08Template\x12\n\n\x02id\x18\x01 \x01(\x05\x12\x0c\n\x04name\x18\x02 \x01(\t\x12\x39\n\tparameter\x18\x03 \x03(\x0b\x32&.FabByExample.proto.symbolic.Parameter\x12=\n\x10mapping_function\x18\x04 \x01(\x0b\x32#.FabByExample.proto.MappingFunction\x12\x35\n\x0c\x66\x65\x61sible_set\x18\x05 \x01(\x0b\x32\x1f.FabByExample.proto.FeasibleSet\x12\x19\n\x11\x63hild_template_id\x18\x06 \x03(\x05\"\xa3\x02\n\x0fMappingFunction\x12<\n\x08linear_3\x18\x01 \x01(\x0b\x32*.FabByExample.proto.LinearMappingFunction3\x12<\n\x08linear_2\x18\x02 \x01(\x0b\x32*.FabByExample.proto.LinearMappingFunction2\x12\x43\n\x0b\x63omposition\x18\x03 \x01(\x0b\x32..FabByExample.proto.CompositionMappingFunction\x12O\n\x12\x65xternal_mesh_file\x18\x04 \x01(\x0b\x32\x33.FabByExample.proto.ExternalMeshFileMappingFunction\"w\n\x1f\x45xternalMeshFileMappingFunction\x12\x14\n\x0cstl_filename\x18\x01 \x01(\t\x12>\n\ttransform\x18\x02 \x01(\x0b\x32+.FabByExample.proto.symbolic.AffineMatrix3S\"1\n\x1a\x43ompositionMappingFunction\x12\x13\n\x0btemplate_id\x18\x01 \x03(\x05\"K\n\x16LinearMappingFunction3\x12\x31\n\x04mesh\x18\x01 \x01(\x0b\x32#.FabByExample.proto.symbolic.Mesh3S\"Q\n\x16LinearMappingFunction2\x12\x37\n\x07\x64rawing\x18\x01 \x01(\x0b\x32&.FabByExample.proto.symbolic.Drawing2S\"J\n\x0b\x46\x65\x61sibleSet\x12;\n\x0f\x63onstraint_list\x18\x01 \x01(\x0b\x32\".FabByExample.proto.ConstraintList\"c\n\x0e\x43onstraintList\x12\x32\n\nconstraint\x18\x01 \x03(\x0b\x32\x1e.FabByExample.proto.Constraint\x12\x1d\n\x15inherited_template_id\x18\x02 \x03(\x05\"M\n\nConstraint\x12?\n\x11linear_constraint\x18\x01 \x01(\x0b\x32$.FabByExample.proto.LinearConstraint\"\xac\x01\n\x10LinearConstraint\x12\x35\n\x04\x65xpr\x18\x01 \x01(\x0b\x32\'.FabByExample.proto.symbolic.LinearExpr\x12\x37\n\x04type\x18\x02 \x01(\x0e\x32).FabByExample.proto.LinearConstraint.Type\"(\n\x04Type\x12\x0c\n\x08\x45QUALITY\x10\x01\x12\x12\n\x0eLESS_THAN_ZERO\x10\x02')



_LINEARCONSTRAINT_TYPE = descriptor.EnumDescriptor(
  name='Type',
  full_name='FabByExample.proto.LinearConstraint.Type',
  filename=None,
  file=DESCRIPTOR,
  values=[
    descriptor.EnumValueDescriptor(
      name='EQUALITY', index=0, number=1,
      options=None,
      type=None),
    descriptor.EnumValueDescriptor(
      name='LESS_THAN_ZERO', index=1, number=2,
      options=None,
      type=None),
  ],
  containing_type=None,
  options=None,
  serialized_start=1401,
  serialized_end=1441,
)


_TEMPLATESET = descriptor.Descriptor(
  name='TemplateSet',
  full_name='FabByExample.proto.TemplateSet',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='template', full_name='FabByExample.proto.TemplateSet.template', index=0,
      number=1, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='root_template_id', full_name='FabByExample.proto.TemplateSet.root_template_id', index=1,
      number=2, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=54,
  serialized_end=141,
)


_TEMPLATE = descriptor.Descriptor(
  name='Template',
  full_name='FabByExample.proto.Template',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='id', full_name='FabByExample.proto.Template.id', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='name', full_name='FabByExample.proto.Template.name', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=unicode("", "utf-8"),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='parameter', full_name='FabByExample.proto.Template.parameter', index=2,
      number=3, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='mapping_function', full_name='FabByExample.proto.Template.mapping_function', index=3,
      number=4, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='feasible_set', full_name='FabByExample.proto.Template.feasible_set', index=4,
      number=5, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='child_template_id', full_name='FabByExample.proto.Template.child_template_id', index=5,
      number=6, type=5, cpp_type=1, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=144,
  serialized_end=384,
)


_MAPPINGFUNCTION = descriptor.Descriptor(
  name='MappingFunction',
  full_name='FabByExample.proto.MappingFunction',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='linear_3', full_name='FabByExample.proto.MappingFunction.linear_3', index=0,
      number=1, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='linear_2', full_name='FabByExample.proto.MappingFunction.linear_2', index=1,
      number=2, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='composition', full_name='FabByExample.proto.MappingFunction.composition', index=2,
      number=3, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='external_mesh_file', full_name='FabByExample.proto.MappingFunction.external_mesh_file', index=3,
      number=4, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=387,
  serialized_end=678,
)


_EXTERNALMESHFILEMAPPINGFUNCTION = descriptor.Descriptor(
  name='ExternalMeshFileMappingFunction',
  full_name='FabByExample.proto.ExternalMeshFileMappingFunction',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='stl_filename', full_name='FabByExample.proto.ExternalMeshFileMappingFunction.stl_filename', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=unicode("", "utf-8"),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='transform', full_name='FabByExample.proto.ExternalMeshFileMappingFunction.transform', index=1,
      number=2, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=680,
  serialized_end=799,
)


_COMPOSITIONMAPPINGFUNCTION = descriptor.Descriptor(
  name='CompositionMappingFunction',
  full_name='FabByExample.proto.CompositionMappingFunction',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='template_id', full_name='FabByExample.proto.CompositionMappingFunction.template_id', index=0,
      number=1, type=5, cpp_type=1, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=801,
  serialized_end=850,
)


_LINEARMAPPINGFUNCTION3 = descriptor.Descriptor(
  name='LinearMappingFunction3',
  full_name='FabByExample.proto.LinearMappingFunction3',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='mesh', full_name='FabByExample.proto.LinearMappingFunction3.mesh', index=0,
      number=1, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=852,
  serialized_end=927,
)


_LINEARMAPPINGFUNCTION2 = descriptor.Descriptor(
  name='LinearMappingFunction2',
  full_name='FabByExample.proto.LinearMappingFunction2',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='drawing', full_name='FabByExample.proto.LinearMappingFunction2.drawing', index=0,
      number=1, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=929,
  serialized_end=1010,
)


_FEASIBLESET = descriptor.Descriptor(
  name='FeasibleSet',
  full_name='FabByExample.proto.FeasibleSet',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='constraint_list', full_name='FabByExample.proto.FeasibleSet.constraint_list', index=0,
      number=1, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=1012,
  serialized_end=1086,
)


_CONSTRAINTLIST = descriptor.Descriptor(
  name='ConstraintList',
  full_name='FabByExample.proto.ConstraintList',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='constraint', full_name='FabByExample.proto.ConstraintList.constraint', index=0,
      number=1, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='inherited_template_id', full_name='FabByExample.proto.ConstraintList.inherited_template_id', index=1,
      number=2, type=5, cpp_type=1, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=1088,
  serialized_end=1187,
)


_CONSTRAINT = descriptor.Descriptor(
  name='Constraint',
  full_name='FabByExample.proto.Constraint',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='linear_constraint', full_name='FabByExample.proto.Constraint.linear_constraint', index=0,
      number=1, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=1189,
  serialized_end=1266,
)


_LINEARCONSTRAINT = descriptor.Descriptor(
  name='LinearConstraint',
  full_name='FabByExample.proto.LinearConstraint',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='expr', full_name='FabByExample.proto.LinearConstraint.expr', index=0,
      number=1, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='type', full_name='FabByExample.proto.LinearConstraint.type', index=1,
      number=2, type=14, cpp_type=8, label=1,
      has_default_value=False, default_value=1,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
    _LINEARCONSTRAINT_TYPE,
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=1269,
  serialized_end=1441,
)

_TEMPLATESET.fields_by_name['template'].message_type = _TEMPLATE
_TEMPLATE.fields_by_name['parameter'].message_type = symbolic_pb2._PARAMETER
_TEMPLATE.fields_by_name['mapping_function'].message_type = _MAPPINGFUNCTION
_TEMPLATE.fields_by_name['feasible_set'].message_type = _FEASIBLESET
_MAPPINGFUNCTION.fields_by_name['linear_3'].message_type = _LINEARMAPPINGFUNCTION3
_MAPPINGFUNCTION.fields_by_name['linear_2'].message_type = _LINEARMAPPINGFUNCTION2
_MAPPINGFUNCTION.fields_by_name['composition'].message_type = _COMPOSITIONMAPPINGFUNCTION
_MAPPINGFUNCTION.fields_by_name['external_mesh_file'].message_type = _EXTERNALMESHFILEMAPPINGFUNCTION
_EXTERNALMESHFILEMAPPINGFUNCTION.fields_by_name['transform'].message_type = symbolic_pb2._AFFINEMATRIX3S
_LINEARMAPPINGFUNCTION3.fields_by_name['mesh'].message_type = symbolic_pb2._MESH3S
_LINEARMAPPINGFUNCTION2.fields_by_name['drawing'].message_type = symbolic_pb2._DRAWING2S
_FEASIBLESET.fields_by_name['constraint_list'].message_type = _CONSTRAINTLIST
_CONSTRAINTLIST.fields_by_name['constraint'].message_type = _CONSTRAINT
_CONSTRAINT.fields_by_name['linear_constraint'].message_type = _LINEARCONSTRAINT
_LINEARCONSTRAINT.fields_by_name['expr'].message_type = symbolic_pb2._LINEAREXPR
_LINEARCONSTRAINT.fields_by_name['type'].enum_type = _LINEARCONSTRAINT_TYPE
_LINEARCONSTRAINT_TYPE.containing_type = _LINEARCONSTRAINT;
DESCRIPTOR.message_types_by_name['TemplateSet'] = _TEMPLATESET
DESCRIPTOR.message_types_by_name['Template'] = _TEMPLATE
DESCRIPTOR.message_types_by_name['MappingFunction'] = _MAPPINGFUNCTION
DESCRIPTOR.message_types_by_name['ExternalMeshFileMappingFunction'] = _EXTERNALMESHFILEMAPPINGFUNCTION
DESCRIPTOR.message_types_by_name['CompositionMappingFunction'] = _COMPOSITIONMAPPINGFUNCTION
DESCRIPTOR.message_types_by_name['LinearMappingFunction3'] = _LINEARMAPPINGFUNCTION3
DESCRIPTOR.message_types_by_name['LinearMappingFunction2'] = _LINEARMAPPINGFUNCTION2
DESCRIPTOR.message_types_by_name['FeasibleSet'] = _FEASIBLESET
DESCRIPTOR.message_types_by_name['ConstraintList'] = _CONSTRAINTLIST
DESCRIPTOR.message_types_by_name['Constraint'] = _CONSTRAINT
DESCRIPTOR.message_types_by_name['LinearConstraint'] = _LINEARCONSTRAINT

class TemplateSet(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _TEMPLATESET
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.TemplateSet)

class Template(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _TEMPLATE
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.Template)

class MappingFunction(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _MAPPINGFUNCTION
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.MappingFunction)

class ExternalMeshFileMappingFunction(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _EXTERNALMESHFILEMAPPINGFUNCTION
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.ExternalMeshFileMappingFunction)

class CompositionMappingFunction(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _COMPOSITIONMAPPINGFUNCTION
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.CompositionMappingFunction)

class LinearMappingFunction3(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _LINEARMAPPINGFUNCTION3
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.LinearMappingFunction3)

class LinearMappingFunction2(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _LINEARMAPPINGFUNCTION2
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.LinearMappingFunction2)

class FeasibleSet(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _FEASIBLESET
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.FeasibleSet)

class ConstraintList(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _CONSTRAINTLIST
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.ConstraintList)

class Constraint(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _CONSTRAINT
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.Constraint)

class LinearConstraint(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _LINEARCONSTRAINT
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.LinearConstraint)

# @@protoc_insertion_point(module_scope)
