# Generated by the protocol buffer compiler.  DO NOT EDIT!

from google.protobuf import descriptor
from google.protobuf import message
from google.protobuf import reflection
from google.protobuf import descriptor_pb2
# @@protoc_insertion_point(imports)



DESCRIPTOR = descriptor.FileDescriptor(
  name='symbolic.proto',
  package='FabByExample.proto.symbolic',
  serialized_pb='\n\x0esymbolic.proto\x12\x1b\x46\x61\x62\x42yExample.proto.symbolic\"6\n\tParameter\x12\n\n\x02id\x18\x01 \x01(\x05\x12\x0c\n\x04name\x18\x02 \x01(\t\x12\x0f\n\x07\x64\x65\x66\x61ult\x18\x03 \x01(\x01\"@\n\nLinearExpr\x12\x14\n\x0cparameter_id\x18\x01 \x03(\x05\x12\r\n\x05\x63oeff\x18\x02 \x03(\x01\x12\r\n\x05\x63onst\x18\x03 \x01(\x01\"q\n\x07Point2S\x12\x32\n\x01x\x18\x01 \x01(\x0b\x32\'.FabByExample.proto.symbolic.LinearExpr\x12\x32\n\x01y\x18\x02 \x01(\x0b\x32\'.FabByExample.proto.symbolic.LinearExpr\"\xa5\x01\n\x07Point3S\x12\x32\n\x01x\x18\x01 \x01(\x0b\x32\'.FabByExample.proto.symbolic.LinearExpr\x12\x32\n\x01y\x18\x02 \x01(\x0b\x32\'.FabByExample.proto.symbolic.LinearExpr\x12\x32\n\x01z\x18\x03 \x01(\x0b\x32\'.FabByExample.proto.symbolic.LinearExpr\"Y\n\x08Vertex2S\x12\n\n\x02id\x18\x01 \x01(\x05\x12\x0c\n\x04name\x18\x02 \x01(\t\x12\x33\n\x05point\x18\x03 \x01(\x0b\x32$.FabByExample.proto.symbolic.Point2S\"J\n\x06\x45\x64ge2S\x12\n\n\x02id\x18\x01 \x01(\x05\x12\x0c\n\x04name\x18\x02 \x01(\t\x12\x12\n\nvertex1_id\x18\x03 \x01(\x05\x12\x12\n\nvertex2_id\x18\x04 \x01(\x05\"F\n\x06\x46\x61\x63\x65\x32S\x12\n\n\x02id\x18\x01 \x01(\x05\x12\x0c\n\x04name\x18\x02 \x01(\t\x12\x11\n\tvertex_id\x18\x03 \x03(\x05\x12\x0f\n\x07\x65\x64ge_id\x18\x04 \x03(\x05\"\xc2\x01\n\tDrawing2S\x12\n\n\x02id\x18\x01 \x01(\x05\x12\x0c\n\x04name\x18\x02 \x01(\t\x12\x35\n\x06vertex\x18\x03 \x03(\x0b\x32%.FabByExample.proto.symbolic.Vertex2S\x12\x31\n\x04\x65\x64ge\x18\x04 \x03(\x0b\x32#.FabByExample.proto.symbolic.Edge2S\x12\x31\n\x04\x66\x61\x63\x65\x18\x05 \x03(\x0b\x32#.FabByExample.proto.symbolic.Face2S\"Y\n\x08Vertex3S\x12\n\n\x02id\x18\x01 \x01(\x05\x12\x0c\n\x04name\x18\x02 \x01(\t\x12\x33\n\x05point\x18\x03 \x01(\x0b\x32$.FabByExample.proto.symbolic.Point3S\"J\n\x06\x45\x64ge3S\x12\n\n\x02id\x18\x01 \x01(\x05\x12\x0c\n\x04name\x18\x02 \x01(\t\x12\x12\n\nvertex1_id\x18\x03 \x01(\x05\x12\x12\n\nvertex2_id\x18\x04 \x01(\x05\"F\n\x06\x46\x61\x63\x65\x33S\x12\n\n\x02id\x18\x01 \x01(\x05\x12\x0c\n\x04name\x18\x02 \x01(\t\x12\x11\n\tvertex_id\x18\x03 \x03(\x05\x12\x0f\n\x07\x65\x64ge_id\x18\x04 \x03(\x05\"\xbf\x01\n\x06Mesh3S\x12\n\n\x02id\x18\x01 \x01(\x05\x12\x0c\n\x04name\x18\x02 \x01(\x05\x12\x35\n\x06vertex\x18\x03 \x03(\x0b\x32%.FabByExample.proto.symbolic.Vertex3S\x12\x31\n\x04\x65\x64ge\x18\x04 \x03(\x0b\x32#.FabByExample.proto.symbolic.Edge3S\x12\x31\n\x04\x66\x61\x63\x65\x18\x05 \x03(\x0b\x32#.FabByExample.proto.symbolic.Face3S\"H\n\x0e\x41\x66\x66ineMatrix3S\x12\x36\n\x05value\x18\x01 \x03(\x0b\x32\'.FabByExample.proto.symbolic.LinearExpr')




_PARAMETER = descriptor.Descriptor(
  name='Parameter',
  full_name='FabByExample.proto.symbolic.Parameter',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='id', full_name='FabByExample.proto.symbolic.Parameter.id', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='name', full_name='FabByExample.proto.symbolic.Parameter.name', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=unicode("", "utf-8"),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='default', full_name='FabByExample.proto.symbolic.Parameter.default', index=2,
      number=3, type=1, cpp_type=5, label=1,
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
  serialized_start=47,
  serialized_end=101,
)


_LINEAREXPR = descriptor.Descriptor(
  name='LinearExpr',
  full_name='FabByExample.proto.symbolic.LinearExpr',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='parameter_id', full_name='FabByExample.proto.symbolic.LinearExpr.parameter_id', index=0,
      number=1, type=5, cpp_type=1, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='coeff', full_name='FabByExample.proto.symbolic.LinearExpr.coeff', index=1,
      number=2, type=1, cpp_type=5, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='const', full_name='FabByExample.proto.symbolic.LinearExpr.const', index=2,
      number=3, type=1, cpp_type=5, label=1,
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
  serialized_start=103,
  serialized_end=167,
)


_POINT2S = descriptor.Descriptor(
  name='Point2S',
  full_name='FabByExample.proto.symbolic.Point2S',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='x', full_name='FabByExample.proto.symbolic.Point2S.x', index=0,
      number=1, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='y', full_name='FabByExample.proto.symbolic.Point2S.y', index=1,
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
  serialized_start=169,
  serialized_end=282,
)


_POINT3S = descriptor.Descriptor(
  name='Point3S',
  full_name='FabByExample.proto.symbolic.Point3S',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='x', full_name='FabByExample.proto.symbolic.Point3S.x', index=0,
      number=1, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='y', full_name='FabByExample.proto.symbolic.Point3S.y', index=1,
      number=2, type=11, cpp_type=10, label=1,
      has_default_value=False, default_value=None,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='z', full_name='FabByExample.proto.symbolic.Point3S.z', index=2,
      number=3, type=11, cpp_type=10, label=1,
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
  serialized_start=285,
  serialized_end=450,
)


_VERTEX2S = descriptor.Descriptor(
  name='Vertex2S',
  full_name='FabByExample.proto.symbolic.Vertex2S',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='id', full_name='FabByExample.proto.symbolic.Vertex2S.id', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='name', full_name='FabByExample.proto.symbolic.Vertex2S.name', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=unicode("", "utf-8"),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='point', full_name='FabByExample.proto.symbolic.Vertex2S.point', index=2,
      number=3, type=11, cpp_type=10, label=1,
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
  serialized_start=452,
  serialized_end=541,
)


_EDGE2S = descriptor.Descriptor(
  name='Edge2S',
  full_name='FabByExample.proto.symbolic.Edge2S',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='id', full_name='FabByExample.proto.symbolic.Edge2S.id', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='name', full_name='FabByExample.proto.symbolic.Edge2S.name', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=unicode("", "utf-8"),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='vertex1_id', full_name='FabByExample.proto.symbolic.Edge2S.vertex1_id', index=2,
      number=3, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='vertex2_id', full_name='FabByExample.proto.symbolic.Edge2S.vertex2_id', index=3,
      number=4, type=5, cpp_type=1, label=1,
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
  serialized_start=543,
  serialized_end=617,
)


_FACE2S = descriptor.Descriptor(
  name='Face2S',
  full_name='FabByExample.proto.symbolic.Face2S',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='id', full_name='FabByExample.proto.symbolic.Face2S.id', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='name', full_name='FabByExample.proto.symbolic.Face2S.name', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=unicode("", "utf-8"),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='vertex_id', full_name='FabByExample.proto.symbolic.Face2S.vertex_id', index=2,
      number=3, type=5, cpp_type=1, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='edge_id', full_name='FabByExample.proto.symbolic.Face2S.edge_id', index=3,
      number=4, type=5, cpp_type=1, label=3,
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
  serialized_start=619,
  serialized_end=689,
)


_DRAWING2S = descriptor.Descriptor(
  name='Drawing2S',
  full_name='FabByExample.proto.symbolic.Drawing2S',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='id', full_name='FabByExample.proto.symbolic.Drawing2S.id', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='name', full_name='FabByExample.proto.symbolic.Drawing2S.name', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=unicode("", "utf-8"),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='vertex', full_name='FabByExample.proto.symbolic.Drawing2S.vertex', index=2,
      number=3, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='edge', full_name='FabByExample.proto.symbolic.Drawing2S.edge', index=3,
      number=4, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='face', full_name='FabByExample.proto.symbolic.Drawing2S.face', index=4,
      number=5, type=11, cpp_type=10, label=3,
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
  serialized_start=692,
  serialized_end=886,
)


_VERTEX3S = descriptor.Descriptor(
  name='Vertex3S',
  full_name='FabByExample.proto.symbolic.Vertex3S',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='id', full_name='FabByExample.proto.symbolic.Vertex3S.id', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='name', full_name='FabByExample.proto.symbolic.Vertex3S.name', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=unicode("", "utf-8"),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='point', full_name='FabByExample.proto.symbolic.Vertex3S.point', index=2,
      number=3, type=11, cpp_type=10, label=1,
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
  serialized_start=888,
  serialized_end=977,
)


_EDGE3S = descriptor.Descriptor(
  name='Edge3S',
  full_name='FabByExample.proto.symbolic.Edge3S',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='id', full_name='FabByExample.proto.symbolic.Edge3S.id', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='name', full_name='FabByExample.proto.symbolic.Edge3S.name', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=unicode("", "utf-8"),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='vertex1_id', full_name='FabByExample.proto.symbolic.Edge3S.vertex1_id', index=2,
      number=3, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='vertex2_id', full_name='FabByExample.proto.symbolic.Edge3S.vertex2_id', index=3,
      number=4, type=5, cpp_type=1, label=1,
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
  serialized_start=979,
  serialized_end=1053,
)


_FACE3S = descriptor.Descriptor(
  name='Face3S',
  full_name='FabByExample.proto.symbolic.Face3S',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='id', full_name='FabByExample.proto.symbolic.Face3S.id', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='name', full_name='FabByExample.proto.symbolic.Face3S.name', index=1,
      number=2, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=unicode("", "utf-8"),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='vertex_id', full_name='FabByExample.proto.symbolic.Face3S.vertex_id', index=2,
      number=3, type=5, cpp_type=1, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='edge_id', full_name='FabByExample.proto.symbolic.Face3S.edge_id', index=3,
      number=4, type=5, cpp_type=1, label=3,
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
  serialized_start=1055,
  serialized_end=1125,
)


_MESH3S = descriptor.Descriptor(
  name='Mesh3S',
  full_name='FabByExample.proto.symbolic.Mesh3S',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='id', full_name='FabByExample.proto.symbolic.Mesh3S.id', index=0,
      number=1, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='name', full_name='FabByExample.proto.symbolic.Mesh3S.name', index=1,
      number=2, type=5, cpp_type=1, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='vertex', full_name='FabByExample.proto.symbolic.Mesh3S.vertex', index=2,
      number=3, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='edge', full_name='FabByExample.proto.symbolic.Mesh3S.edge', index=3,
      number=4, type=11, cpp_type=10, label=3,
      has_default_value=False, default_value=[],
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='face', full_name='FabByExample.proto.symbolic.Mesh3S.face', index=4,
      number=5, type=11, cpp_type=10, label=3,
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
  serialized_start=1128,
  serialized_end=1319,
)


_AFFINEMATRIX3S = descriptor.Descriptor(
  name='AffineMatrix3S',
  full_name='FabByExample.proto.symbolic.AffineMatrix3S',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='value', full_name='FabByExample.proto.symbolic.AffineMatrix3S.value', index=0,
      number=1, type=11, cpp_type=10, label=3,
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
  serialized_start=1321,
  serialized_end=1393,
)

_POINT2S.fields_by_name['x'].message_type = _LINEAREXPR
_POINT2S.fields_by_name['y'].message_type = _LINEAREXPR
_POINT3S.fields_by_name['x'].message_type = _LINEAREXPR
_POINT3S.fields_by_name['y'].message_type = _LINEAREXPR
_POINT3S.fields_by_name['z'].message_type = _LINEAREXPR
_VERTEX2S.fields_by_name['point'].message_type = _POINT2S
_DRAWING2S.fields_by_name['vertex'].message_type = _VERTEX2S
_DRAWING2S.fields_by_name['edge'].message_type = _EDGE2S
_DRAWING2S.fields_by_name['face'].message_type = _FACE2S
_VERTEX3S.fields_by_name['point'].message_type = _POINT3S
_MESH3S.fields_by_name['vertex'].message_type = _VERTEX3S
_MESH3S.fields_by_name['edge'].message_type = _EDGE3S
_MESH3S.fields_by_name['face'].message_type = _FACE3S
_AFFINEMATRIX3S.fields_by_name['value'].message_type = _LINEAREXPR
DESCRIPTOR.message_types_by_name['Parameter'] = _PARAMETER
DESCRIPTOR.message_types_by_name['LinearExpr'] = _LINEAREXPR
DESCRIPTOR.message_types_by_name['Point2S'] = _POINT2S
DESCRIPTOR.message_types_by_name['Point3S'] = _POINT3S
DESCRIPTOR.message_types_by_name['Vertex2S'] = _VERTEX2S
DESCRIPTOR.message_types_by_name['Edge2S'] = _EDGE2S
DESCRIPTOR.message_types_by_name['Face2S'] = _FACE2S
DESCRIPTOR.message_types_by_name['Drawing2S'] = _DRAWING2S
DESCRIPTOR.message_types_by_name['Vertex3S'] = _VERTEX3S
DESCRIPTOR.message_types_by_name['Edge3S'] = _EDGE3S
DESCRIPTOR.message_types_by_name['Face3S'] = _FACE3S
DESCRIPTOR.message_types_by_name['Mesh3S'] = _MESH3S
DESCRIPTOR.message_types_by_name['AffineMatrix3S'] = _AFFINEMATRIX3S

class Parameter(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _PARAMETER
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.symbolic.Parameter)

class LinearExpr(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _LINEAREXPR
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.symbolic.LinearExpr)

class Point2S(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _POINT2S
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.symbolic.Point2S)

class Point3S(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _POINT3S
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.symbolic.Point3S)

class Vertex2S(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _VERTEX2S
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.symbolic.Vertex2S)

class Edge2S(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _EDGE2S
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.symbolic.Edge2S)

class Face2S(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _FACE2S
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.symbolic.Face2S)

class Drawing2S(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _DRAWING2S
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.symbolic.Drawing2S)

class Vertex3S(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _VERTEX3S
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.symbolic.Vertex3S)

class Edge3S(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _EDGE3S
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.symbolic.Edge3S)

class Face3S(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _FACE3S
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.symbolic.Face3S)

class Mesh3S(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _MESH3S
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.symbolic.Mesh3S)

class AffineMatrix3S(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _AFFINEMATRIX3S
  
  # @@protoc_insertion_point(class_scope:FabByExample.proto.symbolic.AffineMatrix3S)

# @@protoc_insertion_point(module_scope)
