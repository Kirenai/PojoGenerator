import { useEffect, useState } from 'react'
import { useActionData, useSubmit } from 'react-router-dom'
import { Box, Textarea, VStack } from '@chakra-ui/react'
import { Field, Form, Formik } from 'formik'
import { Alert } from '@/components/ui/alert'
import { Button } from '@/components/ui/button'
import { Payload } from '@/modules/pojo/domain/model/payload'
import { ActionResponse } from '@/modules/pojo/domain/model/types'
import DownloadButton from './DownloadButton'

const initialValues: Payload = {
  text: '',
}

const PojoForm = () => {
  const submit = useSubmit()
  const placeholder =
    'person.id - I\nperson.name - I\nperson.age - I\ndata.person.id - O\ndata.person.name - O\ndata.person.age - O'

  const actionData = useActionData() as ActionResponse | undefined
  const [zipUrl, setZipUrl] = useState<string | null>(null)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    if (actionData?.success && actionData.zipUrl !== zipUrl) {
      setZipUrl(actionData.zipUrl)
      setError(null)
    }

    if (actionData?.success === false && actionData.error) {
      setError(actionData.error)
    }

    return () => {}
  }, [actionData, zipUrl])

  const handleOnClose = () => {
    setError(null)
  }

  return (
    <VStack
      data-state="open"
      _open={{
        animationName: 'slide-from-top, fade-in',
        animationDuration: 'slow',
      }}
      w="full"
      gap="0.5"
    >
      <Formik
        initialValues={initialValues}
        onSubmit={values => {
          try {
            const formData = new FormData()
            formData.append('payload', JSON.stringify(values))
            submit(formData, { method: 'post', action: '/pojo' })
          } catch (error) {
            console.error('Error serializing form data', error)
          }
        }}
      >
        {() => (
          <Form>
            <VStack>
              <Field
                fontFamily="Roboto Mono"
                w={{
                  base: '90.90vw',
                  md: '80.80vw',
                  xl: '60.60vw',
                  '2xl': '40.40vw',
                }}
                minH={{ base: '70.70vh', sm: '50.50vh', md: '65.65vh' }}
                as={Textarea}
                size="xl"
                name="text"
                placeholder={placeholder}
                colorPalette="teal"
                fontWeight="700"
              />
              <Button
                alignSelf="start"
                type="submit"
                variant="surface"
                colorPalette="teal"
                size="xl"
                w={{ base: 'full', sm: '180px' }}
              >
                Submit
              </Button>
            </VStack>
          </Form>
        )}
      </Formik>
      <Box
        w={{
          base: '90.90vw',
          md: '80.80vw',
          xl: '60.60vw',
          '2xl': '40.40vw',
        }}
      >
        {zipUrl && <DownloadButton zipUrl={zipUrl} />}
        {error && (
          <Alert
            mt="4"
            w="full"
            title="Request Error"
            status="error"
            variant="surface"
            closable={true}
            onClose={handleOnClose}
          >
            {error}
          </Alert>
        )}
      </Box>
    </VStack>
  )
}

export default PojoForm
