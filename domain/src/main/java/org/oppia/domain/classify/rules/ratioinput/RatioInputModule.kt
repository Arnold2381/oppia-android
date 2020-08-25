package org.oppia.domain.classify.rules.ratioinput

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import org.oppia.domain.classify.RuleClassifier
import org.oppia.domain.classify.rules.RatioExpressionInputRules

/** Module that binds rule classifiers corresponding to the ratio input interaction. */
@Module
class RatioInputModule {
  @Provides
  @IntoMap
  @StringKey("Equals")
  @RatioExpressionInputRules
  internal fun provideRatioInputEqualsRuleClassifier(
    classifierProvider: RatioInputEqualsRuleClassifierProvider
  ): RuleClassifier = classifierProvider.createRuleClassifier()
}