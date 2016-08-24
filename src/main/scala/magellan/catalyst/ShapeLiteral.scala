/**
 * Copyright 2015 Ram Sriharsha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package magellan.catalyst

import magellan.Shape
import org.apache.spark.sql.catalyst.InternalRow
import org.apache.spark.sql.catalyst.expressions._
import org.apache.spark.sql.catalyst.expressions.codegen.{CodeGenContext, GeneratedExpressionCode}
import org.apache.spark.sql.types.DataType

case class ShapeLiteral(shape: Shape) extends LeafExpression with MagellanExpression {

  private val serialized = serialize(shape)

  override def foldable: Boolean = true

  override def nullable: Boolean = false

  override val dataType: DataType = shape

  override def eval(input: InternalRow) = serialized

  override protected def genCode(ctx: CodeGenContext, ev: GeneratedExpressionCode): String = ???
}
